package com.example.myapp.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.DataStoring
import com.example.myapp.data.AppRepository
import com.example.myapp.data.statistics.Meal
import com.example.myapp.data.user.User
import com.example.myapp.data.user_filter.FilterNames
import com.example.myapp.data.user_filter.getBit
import com.example.myapp.data.user_filter.getNewFilters
import com.example.myapp.data.user_filter.nameToBit
import com.example.myapp.data.user_filter.setBit
import com.example.myapp.dataStore
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.signup.toUser
import com.example.myapp.ui.signup.toUserDetails
import com.example.myapp.ui.signup.toUserUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to maintain Home Screen.
 */
class HomeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private var _currentName: MutableState<String> = mutableStateOf("")

    fun updateName(name: String) {
        _currentName.value = name
    }

    private var _currentFilters: MutableState<List<String>> = mutableStateOf(emptyList())
    val currentFilters by _currentFilters

    private var _currentMeals: MutableState<List<Meal>> = mutableStateOf(emptyList())
    val currentMeals by _currentMeals

    /**
     * Updates an [User] in the Room database
     */
    suspend fun updateUser(userDetails: UserDetails) {
        appRepository.updateUser(userDetails.toUser())
    }

    /**
     * Returns the current [UserDetails]
     */
    val currentUser: MutableState<UserDetailsUiState> = mutableStateOf(UserDetailsUiState())

    suspend fun getUser() {
        viewModelScope.launch {
            appRepository.getUserStream(_currentName.value)
                .filterNotNull()
                .map {
                    UserDetailsUiState(userDetails = it.toUserDetails())
                }.collect {
                    currentUser.value = it
                }
        }
    }

    /**
     * Returns the current [UserDetails]
     */
    suspend fun checkCurrentFilters() {
        val currentFilter = appRepository.getCurrentFilters(_currentName.value)
        FilterNames.values().forEach {
            val filterBit = currentFilter.getBit(nameToBit[it.name]!!)
            if (filterBit == 1){
                if (!_currentFilters.value.contains(it.name)){
                    _currentFilters.value = _currentFilters.value + it.name
                }
            }
        }
    }

    suspend fun updateFilters(uiSelectedBits: MutableList<Int>) {
        // filter Integer of current User
        val currentFilter = appRepository.getCurrentFilters(_currentName.value)
        var updatedFilter = 0

        FilterNames.values().forEach {
            // bit of each FilterNames value
            val currentBit = nameToBit[it.name]!!

            // if bit of current Filter Name is selected on UI
            val isSelected = uiSelectedBits.contains(currentBit)
            if (isSelected) {
               updatedFilter += currentFilter.setBit(currentBit, 1)
            }
        }

        // push updates
        appRepository.updateFilters(_currentName.value, updatedFilter)
    }

    suspend fun checkCurrentMeals() {
        appRepository.getMealsOfUserStream(_currentName.value).forEach {
            _currentMeals.value = it.mealsOfUser
        }
    }

}

/**
 * UI state for UserDetailsScreen
 */
data class UserDetailsUiState(
    val userDetails: UserDetails = UserDetails()
)