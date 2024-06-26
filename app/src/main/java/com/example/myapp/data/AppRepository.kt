package com.example.myapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapp.data.relations.UserWithMeals
import com.example.myapp.data.statistics.Meal
import com.example.myapp.data.user.User
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    //USERS
    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(username: String): Flow<User?>

    suspend fun getMealsOfUserStream(username: String): List<UserWithMeals>

    suspend fun setDish(username: String, mealType: String, dishId: Int)

    suspend fun setSoup(username: String, mealType: String, soupId: Int)

    suspend fun setSalad(username: String, mealType: String, saladId: Int)

    suspend fun setSnack(username: String, mealType: String, snackId: Int)

    suspend fun insertMeal(item: Meal)

    suspend fun insertUser(item: User)

    suspend fun deleteUser(item: User)

    suspend fun updateUser(item: User)

    suspend fun getCurrentFilters(username: String): Int

    suspend fun updateFilters(username: String, newfilters: Int)
}