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

    private var _currentName: String = ""

    private val filterList = listOf(
        "diabetes",
        "allergy",
        "fat",
        "gastritis",
        "noMeat",
        "vegan",
        "noMilk",
        "pregnant",
        "lactation"
    )

    fun updateName(name: String) {
        _currentName = name
    }

    /**
     * Returns the current [UserDetails]
     */
    val currentUser: MutableState<UserDetailsUiState> = mutableStateOf(UserDetailsUiState())
}