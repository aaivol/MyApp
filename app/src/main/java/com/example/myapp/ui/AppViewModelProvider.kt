package com.example.myapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapp.Dependencies
import com.example.myapp.ui.signup.SignUpViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for SignUp
        initializer {
            SignUpViewModel(
                dependencies().container.appRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [Dependencies].
 */
fun CreationExtras.dependencies(): Dependencies =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Dependencies)