package com.example.myapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.user.User

//Diet has N Users, User has 1 Diet
data class DietWithUsers (
    @Embedded val diet: Diet,
    @Relation(
        // Parent = Diet, entity = User
        parentColumn = "id",
        entityColumn = "dietId"
    )
    val users: List<User>
)