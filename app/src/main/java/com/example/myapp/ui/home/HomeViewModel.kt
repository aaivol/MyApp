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
 * ViewModel to get all users in the Room database.
 */
class HomeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private var _currentName: String = ""

    fun updateName(name: String) {
        _currentName = name
    }


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

    ///////
    /**
     * Returns the [User] found with passed [username]
     */
    suspend fun findUser(name: String): User {
        return appRepository.getUserStream(name)
            .filterNotNull()
            .first()
    }

    /**
     * Returns the [id] of current User for [Room] relations
     */
    suspend fun getCurrentId(uiState: UserDetails): String {
        val userDbState = findUser(uiState.username)
            .toUserUiState(true)

        return userDbState.userDetails.id.toString()
    }

}

/**
 * UI state for ItemDetailsScreen
 */
data class UserDetailsUiState(
    val userDetails: UserDetails = UserDetails()
)