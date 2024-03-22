package com.example.myapp.ui.home

import android.content.Context
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

}

/**
 * UI state for ItemDetailsScreen
 */
data class UserDetailsUiState(
    val userDetails: UserDetails = UserDetails()
)