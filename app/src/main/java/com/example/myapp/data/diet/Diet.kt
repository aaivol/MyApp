package com.example.myapp.data.diet

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "diets"
)
data class Diet (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quote: String
)