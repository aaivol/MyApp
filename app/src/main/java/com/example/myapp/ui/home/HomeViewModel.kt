package com.example.myapp.ui.home

import android.content.Context
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to get all users in the Room database.
 */
class HomeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    var currentName by mutableStateOf("")
        private set

    fun updateName(name: String){
        currentName = name
    }

    val uiState: StateFlow<UserUiState> =
        appRepository.getUserStream(currentName)
            .filterNotNull()
            .map {
                UserUiState(userDetails = it.toUserDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = UserUiState()
            )

    /**
     * Returns the [User] found with passed [username]
     */
     suspend fun findUser(name: String): User{
        return appRepository.getUserStream(name)
            .filterNotNull()
            .first()
    }

    /**
     * Returns the [id] of current User for [Room] relations
     */
    suspend fun getCurrentId(uiState: UserDetails): String{
        val userDbState = findUser(uiState.username)
            .toUserUiState(true)

        return userDbState.userDetails.id.toString()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    /**
     * Returns all [Users] from database
     *
    val homeUiState: StateFlow<HomeUiState> =
        appRepository.getAllUsersStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    */
}

/**
 * Ui State for HomeScreen
 */
//data class HomeUiState(val userList: List<User> = listOf())