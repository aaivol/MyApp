package com.example.myapp.data.statistics

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val mealId: Int = 0,
    val mealType: String,
    val username: String,

    val dishId: Int,
    val soupId: Int,
    val saladId: Int,
    val snackId: Int
)
