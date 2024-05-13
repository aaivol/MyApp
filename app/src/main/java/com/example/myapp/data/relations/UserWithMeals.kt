package com.example.myapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapp.data.statistics.Meal
import com.example.myapp.data.user.User

data class UserWithMeals (
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val mealsOfUser: List<Meal>
)