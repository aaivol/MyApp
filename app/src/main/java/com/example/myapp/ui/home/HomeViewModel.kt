package com.example.myapp.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.DataStoring
import com.example.myapp.data.AppRepository
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

    private var _currentName: String = ""

    fun updateName(name: String) {
        _currentName = name
    }

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
            appRepository.getUserStream(_currentName)
                .filterNotNull()
                .map {
                    UserDetailsUiState(userDetails = it.toUserDetails())
                }.collect {
                    currentUser.value = it
                }
        }
    }

    suspend fun checkCurrentFilters() {
        val currentFilter = appRepository.getCurrentFilters(_currentName)

        FilterNames.values().forEach {
            val currentBit = currentFilter.getBit(nameToBit[it.name]!!).toString()
            Log.d("CHECK_FILTER", it.filterName)
            Log.d("BIT", currentBit)
        }
    }

    suspend fun updateFilters(uiSelectedBits: MutableList<Int>) {
        // filter Integer of current User
        val currentFilter = appRepository.getCurrentFilters(_currentName)
        var updatedFilter = 0

        FilterNames.values().forEach {
            // bit of each FilterNames value
            val currentBit = nameToBit[it.name]!!

            // if bit of current Filter Name is selected on UI
            val isSelected = uiSelectedBits.contains(currentBit)
            if (isSelected) {
               updatedFilter = currentFilter.setBit(currentBit, 1)
            }
        }

        // push updates
        appRepository.updateFilters(_currentName, updatedFilter)
    }

}

/**
 * UI state for UserDetailsScreen
 */
data class UserDetailsUiState(
    val userDetails: UserDetails = UserDetails()
)