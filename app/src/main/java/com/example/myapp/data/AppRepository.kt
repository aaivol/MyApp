package com.example.myapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.relations.DietWithUsers
import com.example.myapp.data.user.User
import com.example.myapp.data.user_filter.UserFilter
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    //USERS
    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(username: String): Flow<User?>

    suspend fun updateDietOfUser(username: String, dietId: Int)

    suspend fun insertUser(item: User)

    suspend fun deleteUser(item: User)

    suspend fun updateUser(item: User)

    //DIETS
    fun getDietWithUsersStream(dietId: Int): Flow<List<DietWithUsers>>

    suspend fun insertDiet(item: Diet)

    suspend fun deleteDiet(item: Diet)

    suspend fun updateDiet(item: Diet)

    //USER FILTERS
    suspend fun updateUserFilter(filter: UserFilter)
}