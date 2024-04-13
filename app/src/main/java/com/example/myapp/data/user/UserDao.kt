package com.example.myapp.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
}