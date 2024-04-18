package com.example.myapp.ui.filters

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapp.data.AppRepository
import com.example.myapp.ui.home.UserDetailsUiState
import com.example.myapp.ui.signup.UserDetails

/**
 * ViewModel to maintain Home Screen.
 */
class FiltersViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
}