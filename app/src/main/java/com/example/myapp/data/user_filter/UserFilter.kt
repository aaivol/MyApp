package com.example.myapp.data.user_filter

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_filter"
)
data class UserFilter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,

    val diabetes: Int = 0,
    val allergy: Int = 0,
    val fat: Int = 0,
    val gastritis: Int = 0,
    val noMeat: Int = 0,
    val vegan: Int = 0,
    val noMilk: Int = 0,
    val pregnant: Int = 0,
    val lactation: Int = 0
)