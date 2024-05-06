package com.example.myapp.ui.diet

import androidx.lifecycle.ViewModel
import com.example.myapp.data.AppRepository
import com.example.myapp.data.diet.Diet
import kotlinx.coroutines.flow.first

class DietViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    //insert 5 diets
    private val dietList = listOf(
        Diet(
            1,
            "Похудеть",
            "Самоотречение и воля! Вы решили похудеть"),
        Diet(
            2,
            "Набрать вес",
            "Сила и красота! Вы решили набрать вес"),
        Diet(3,
            "Нарастить мышцы",
            "Мощь внутри вас! Вы хотите увеличить мышечную массу"),
        Diet(4,
            "Поддерживать форму",
            "Стабильность во всем! Вы хотите быть в тонусе"),
        Diet(5,
            "Просто следить за питанием",
            "Правильный рацион! Вы следите за своим питанием")
    )

    fun getDietProps(dietId: Int): Diet {
        return dietList.find { it.id == dietId }!!
    }
}