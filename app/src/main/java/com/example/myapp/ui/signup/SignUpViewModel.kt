package com.example.myapp.ui.signup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.user.User
import com.example.myapp.data.AppRepository
import com.example.myapp.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import androidx.datastore.preferences.core.edit
import com.example.myapp.data.statistics.Meal

/**
 * ViewModel to validate and insert users in the Room database .
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
     * Returns the [User] found with passed [username]
     */
    private suspend fun findUser(name: String): User{
        return appRepository.getUserStream(name)
            .filterNotNull()
            .first()
    }

    /**
     * Checks that [password] from [LoginScreen] matches with [password] from [database] via username.
     */
    suspend fun tryLogin(uiState: UserDetails): Boolean {

        var passwFromDb = ""

        if (validateInput()) {
            val userDbState = findUser(uiState.username)
                .toUserUiState(true)

            passwFromDb = userDbState.userDetails.password

            return passwFromDb == uiState.password
        }
        return false
    }

    /**
     * Inserts an [User] in the Room database
     */
    suspend fun saveUser() {
        if (validateInput()) {
            appRepository.insertUser(userUiState.userDetails.toUser())
            setUserMeals()
        }
    }

    /**
     * Validates not null definitions of [username] and [password]
     */
    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank()
        }
    }

    suspend fun setUsername(context: Context, name: String) {
        context.dataStore.updateData {
            it.copy(
                user = com.example.myapp.User(name)
            )
        }
    }

    suspend fun setUserMeals(){
        val userMeals = listOf<Meal>(
            Meal( mealType = "breakfast",
                username = userUiState.userDetails.username,
                dishId = 0,
                soupId = 0,
                saladId = 0,
                snackId = 0
            ),
            Meal( mealType = "lunch",
                username = userUiState.userDetails.username,
                dishId = 0,
                soupId = 0,
                saladId = 0,
                snackId = 0
            ),
            Meal( mealType = "dinner",
                username = userUiState.userDetails.username,
                dishId = 0,
                soupId = 0,
                saladId = 0,
                snackId = 0
            )
        )

        userMeals.forEach { appRepository.insertMeal(it) }

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
    var dietId: String = "",
    var filters: String = ""
)

fun UserDetails.toUser(): User = User(
    id = id,
    username = username,
    password = password,
    dietId = dietId.toIntOrNull() ?: 0,
    filters = filters.toIntOrNull() ?: 0
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
    dietId = dietId.toString(),
    filters = filters.toString()
)