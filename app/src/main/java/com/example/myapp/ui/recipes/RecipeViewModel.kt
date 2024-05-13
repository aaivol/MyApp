package com.example.myapp.ui.recipes

import androidx.compose.material.icons.Icons
import androidx.lifecycle.ViewModel
import com.example.myapp.R
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
            "Основное блюдо",
            R.drawable.carbonara,
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
            R.drawable.salad,
            "15 минут",
            listOf("Огурцы", "Томаты", "Тофу"),
            55,
            listOf(3, 3, 5),
            listOf("хлоп"),
            // Empty list means recipe [matches all filters]
            emptyList()
        ),
        Recipe(
            3,
            "Творожная запеканка с тыквой",
            "Основное блюдо",
            R.drawable.tykva,
            "50 минут",
            listOf("Сметана", "Тыква", "Творог", "Зелень"),
            150,
            listOf(12, 8, 8),
            listOf("+"),
            // Empty list means recipe [matches all filters]
            listOf(
                FilterNames.DIABETES.toString(),
                FilterNames.ALLERGY.toString(),
                FilterNames.FAT.toString(),
                FilterNames.GASTRITIS.toString(),
                FilterNames.NO_MEAT.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        ),
        Recipe(
            4,
            "Семга на пару",
            "Основное блюдо",
            R.drawable.semga,
            "20 минут",
            listOf("Стейк семги", "Розмарин"),
            197,
            listOf(19, 14, 1),
            listOf("+"),
            // Empty list means recipe [matches all filters]
            listOf(
                FilterNames.DIABETES.toString(),
                FilterNames.FAT.toString(),
                FilterNames.GASTRITIS.toString(),
                FilterNames.NO_MEAT.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        ),
        Recipe(
            5,
            "Гратен Дофинуа",
            "Основное блюдо",
            R.drawable.graten,
            "40 минут",
            listOf("Картофель", "Сливки", "Мускатный орех"),
            172,
            listOf(8, 23, 11),
            listOf("расрас и готово"),
            // Empty list means recipe [matches all filters]
            listOf(
                FilterNames.ALLERGY.toString(),
                FilterNames.GASTRITIS.toString(),
                FilterNames.NO_MEAT.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        ),
        Recipe(
            6,
            "Cуп из цветной капусты",
            "Суп",
            R.drawable.soup_capusta,
            "35 минут",
            listOf("Цветная капуста", "Репчатый лук", "Картофель", "Морковь"),
            31,
            listOf(1, 1, 4),
            listOf("+"),
            emptyList()
        ),
        Recipe(
            7,
            "Рис с грибами и томатами",
            "Перекус",
            R.drawable.rice ,
            "15 минут",
            listOf("Рис", "Шампиньоны", "Томаты"),
            55,
            listOf(1, 2, 30),
            listOf("+"),
            // Empty list means recipe [matches all filters]
            emptyList()
        ),
        Recipe(
            8,
            "Тест",
            "Основное блюдо",
            R.drawable.ic_launcher_background,
            "15 минут",
            listOf("Рис", "Шампиньоны", "Томаты"),
            55,
            listOf(1, 2, 30),
            listOf("+"),
            // Empty list means recipe [matches all filters]
            emptyList()
        ),
        Recipe(
            9,
            "Борщ",
            "Суп",
            R.drawable.ic_launcher_background,
            "1 час",
            listOf("Свекла", "Говядина", "Картофель", "Морковь"),
            50,
            listOf(1, 8, 2),
            listOf("+"),
            listOf(
                FilterNames.DIABETES.toString(),
                FilterNames.FAT.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        ),
        Recipe(
            10,
            "Цезарь с морепродуктами",
            "Салат",
            R.drawable.ic_launcher_background,
            "1 час",
            listOf("Яйцо", "Салат айсберг", "Креветки", "Кальмары"),
            77,
            listOf(6, 4, 3),
            listOf("+"),
            emptyList()
        ),
    )
}