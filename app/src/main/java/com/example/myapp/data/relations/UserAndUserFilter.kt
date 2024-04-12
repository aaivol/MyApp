package com.example.myapp.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapp.data.user.User
import com.example.myapp.data.user_filter.UserFilter

//User has 1 UserFilter, UserFilter has 1 User
data class UserAndUserFilter (
    @Embedded val user: User,
    @Relation(
        // Parent = User, entity = UserFilter
        parentColumn = "id",
        entityColumn = "userId"
    )
    val filters: UserFilter
)