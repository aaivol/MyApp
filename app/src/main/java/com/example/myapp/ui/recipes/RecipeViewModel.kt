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
            "Основное",
            "",
            "30 минут",
            listOf("Ветчина", "Яйцо", "Сливки 20%", "Чеснок", "Паста"),
            275,
            listOf(11, 15, 27),
            listOf("мяу", "миу"),
            listOf(
                FilterNames.ALLERGY.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        ),
        Recipe(
            2,
            "Весенний салат",
            "Салат",
            "",
            "15 минут",
            listOf("Огурцы", "Томаты", "Тофу"),
            55,
            listOf(3, 3, 5),
            listOf("хлоп"),
            // Empty list means recipe [matches all filters]
            emptyList()
        )
    )
}