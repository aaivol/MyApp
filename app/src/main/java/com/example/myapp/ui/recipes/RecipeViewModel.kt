package com.example.myapp.ui.recipes

import androidx.lifecycle.ViewModel
import com.example.myapp.data.AppRepository
import com.example.myapp.data.food.Recipe
import com.example.myapp.data.user_filter.FilterNames

class RecipeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    val recipes = listOf(
        Recipe(
            1,
            "Карбонара",
            "",
            listOf("Ветчина", "Яйцо", "Сливки 20%", "Чеснок", "Паста"),
            listOf("мяу", "миу"),
            listOf(FilterNames.ALLERGY, FilterNames.PREGNANT, FilterNames.LACTATION)
        )
    )
}