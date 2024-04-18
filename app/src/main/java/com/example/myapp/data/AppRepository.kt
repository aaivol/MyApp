package com.example.myapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.myapp.data.user.User
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    //USERS
    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(username: String): Flow<User?>

    suspend fun insertUser(item: User)

    suspend fun deleteUser(item: User)

    suspend fun updateUser(item: User)

    suspend fun getCurrentFilters(username: String): Int

    suspend fun updateFilters(username: String, newfilters: Int)
}