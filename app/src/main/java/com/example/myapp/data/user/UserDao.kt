package com.example.myapp.data.user

import android.health.connect.datatypes.MealType
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapp.data.relations.UserWithMeals
import com.example.myapp.data.statistics.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from users WHERE username = :username")
    fun getUser(username: String) : Flow<User>

    @Query("SELECT * from users ORDER BY username ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("UPDATE users SET dietId = :dietId WHERE username = :username")
    suspend fun updateDietOfUser(username: String, dietId: Int)

    @Query("SELECT filters from users WHERE username = :username")
    suspend fun getCurrentFilters(username: String): Int

    @Query("UPDATE users SET filters = :newfilters WHERE username = :username")
    suspend fun updateFilters(username: String, newfilters: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("UPDATE meal SET dishId = :dishId WHERE (username = :username AND mealType = :mealType) " )
    suspend fun updateMeal(username: String, mealType: String, dishId: Int)
    //update(test, breakfast, 5)

    @Transaction
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getMealsOfUser(username: String): List<UserWithMeals>
}