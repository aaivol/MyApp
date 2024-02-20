package com.example.myapp.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.AppRepository
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.signup.toUserUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel validate [User] during LOGIN from the [AppRepository]'s data source.
 */
class LoginViewModel (
    savedStateHandle: SavedStateHandle,
    private val appRepository : AppRepository
) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set

    private val userName: String = checkNotNull(savedStateHandle[LoginDestination.userNameArg])

    init {
        viewModelScope.launch {
            userUiState = appRepository.getUserStream(userName)
                .filterNotNull()
                .first()
                .toUserUiState(true)
        }
    }

    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank()
        }
    }
}