package com.example.myapp.ui.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapp.data.user.User
import com.example.myapp.data.AppRepository
import java.text.NumberFormat

/**
 * ViewModel to validate and insert users in the Room database = sign up.
 */
class SignUpViewModel(private val appRepository: AppRepository) : ViewModel() {
    /**
     * Holds current user ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set

    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    /**
     * Inserts an [User] in the Room database
     */
    suspend fun saveUser() {
        if (validateInput()) {
            appRepository.insertUser(userUiState.userDetails.toUser())
        }
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank() && dietKey.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for User.
 */
data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

data class UserDetails(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val dietKey: String = "",
)

/**
 * Extension function to convert [UserDetails] to [User]. If the value of [UserDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [UserDetails.quantity] is not a valid [Int], then the quantity will be set to 0
 */
fun UserDetails.toUser(): User = User(
    id = id,
    username = username,
    password = password,
    dietKey = dietKey.toIntOrNull() ?: 0,
)

//fun User.formatedPassword(): String {

/**
 * Extension function to convert [User] to [UserUiState]
 */
fun User.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [User] to [UserDetails]
 */
fun User.toUserDetails(): UserDetails = UserDetails(
    id = id,
    username = username,
    password = password,
    dietKey = dietKey.toString()
)