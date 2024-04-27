package com.example.myapp.data.food

import com.example.myapp.data.user_filter.FilterNames

data class Recipe(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val ingres: List<String>,
    val steps: List<String>,
    val suitableFilters: List<FilterNames>
)