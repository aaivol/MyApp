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
import com.example.myapp.ui.signup.toUser
import com.example.myapp.ui.signup.toUserDetails
import com.example.myapp.ui.signup.toUserUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel validate [User] during LOGIN from the [AppRepository]'s data source.
 */
class LoginViewModel (
    private val appRepository : AppRepository
) : ViewModel() {

}