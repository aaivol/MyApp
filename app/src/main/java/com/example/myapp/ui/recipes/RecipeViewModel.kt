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
                FilterNames.ALLERGY.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
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
                FilterNames.DIABETES.filterName,
                FilterNames.ALLERGY.filterName,
                FilterNames.FAT.filterName,
                FilterNames.GASTRITIS.filterName,
                FilterNames.NO_MEAT.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
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
                FilterNames.DIABETES.filterName,
                FilterNames.FAT.filterName,
                FilterNames.GASTRITIS.filterName,
                FilterNames.NO_MEAT.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
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
                FilterNames.ALLERGY.filterName,
                FilterNames.GASTRITIS.filterName,
                FilterNames.NO_MEAT.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
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
            "Молочный коктейль",
            "Напиток",
            R.drawable.choco_coctail,
            "1 час 15 минут",
            listOf("Молоко", "Шоколад", "Сахар"),
            300,
            listOf(7, 27, 32),
            listOf("+"),
            listOf(
                FilterNames.NO_MEAT.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
            )
        ),
        Recipe(
            9,
            "Борщ",
            "Суп",
            R.drawable.bors,
            "1 час",
            listOf("Свекла", "Говядина", "Картофель", "Морковь"),
            50,
            listOf(1, 8, 2),
            listOf("+"),
            listOf(
                FilterNames.DIABETES.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
            )
        ),
        Recipe(
            10,
            "Салат с лососем",
            "Салат",
            R.drawable.salad_with_losos,
            "1 час",
            listOf("Томаты", "Лосось", "Айсберг", "Пармезан"),
            90,
            listOf(6, 4, 3),
            listOf("+"),
            listOf(
                FilterNames.NO_MEAT.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
            )
        ),
        Recipe(
            11,
            "Мохито",
            "Напиток",
            R.drawable.mojito,
            "20 минут",
            listOf("Лайм", "Текила", "Спрайт"),
            80,
            listOf(0, 0, 50),
            listOf("+"),
            listOf(
                FilterNames.NO_MEAT.filterName,
                FilterNames.NO_MILK.filterName
            )
        ),
    )
}