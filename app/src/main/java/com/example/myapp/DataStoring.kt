package com.example.myapp

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class DataStoring(
    val user: User = User("default")
)

@Serializable
data class User(
    val name: String
)