package com.example.myapp.data

import com.example.myapp.data.diet.Diet
import com.example.myapp.data.relations.DietWithUsers
import com.example.myapp.data.user.User
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    //USERS
    fun getAllUsersStream(): Flow<List<User>>
    /**
     * Retrieve an item from the given data source that matches with the username.
     */
    fun getUserStream(username: String): Flow<User?>

    suspend fun insertUser(item: User)

    suspend fun deleteUser(item: User)

    suspend fun updateUser(item: User)


    //DIETS
    /**
     * Retrieve relation between Diet and Users 1-N.
     */
    fun getDietWithUsersStream(dietId: Int): Flow<List<DietWithUsers>>

    suspend fun insertDiet(item: Diet)

    suspend fun deleteDiet(item: Diet)

    suspend fun updateDiet(item: Diet)
}