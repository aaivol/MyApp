package com.example.myapp.data.food

import com.example.myapp.data.user_filter.FilterNames

data class Recipe(
    val id: Int,
    val name: String,
    val type: String,
    val imageUrl: String,
    val time: String,
    val ingredients: List<String>,
    val calories: Int,
    val pfc: List<Int>,
    val steps: List<String>,
    val suitableFilters: List<String>
){
    fun compareFilters(currentUserFilter: List<String>): Boolean {
        currentUserFilter.forEach {
           if (!suitableFilters.contains(it)){
               return false
           }
        }
        return true
    }
}