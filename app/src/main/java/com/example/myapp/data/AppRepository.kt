package com.example.myapp.data

import com.example.myapp.data.user.User
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    /**
     * Retrieve all the items from the given data source.
     */
    fun getAllUsersStream(): Flow<List<User>>

    /**
     * Retrieve an item from the given data source that matches with the id.
     */
    fun getUserStream(username: String): Flow<User?>

    /**
     * Insert item in the data source
     */
    suspend fun insertUser(item: User)

    /**
     * Delete item from the data source
     */
    suspend fun deleteUser(item: User)

    /**
     * Update item in the data source
     */
    suspend fun updateUser(item: User)
}