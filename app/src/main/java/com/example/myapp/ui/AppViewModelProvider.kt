package com.example.myapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapp.MyApp
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.signup.SignUpViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for SignUp
        initializer {
            SignUpViewModel(
                myapp().container.appRepository
            )
        }
        initializer {
            HomeViewModel(
                myapp().container.appRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [MyApp].
 */
fun CreationExtras.myapp(): MyApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApp)