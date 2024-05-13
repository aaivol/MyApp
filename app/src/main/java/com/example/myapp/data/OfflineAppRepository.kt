package com.example.myapp.data

import com.example.myapp.data.relations.UserWithMeals
import com.example.myapp.data.statistics.Meal
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import kotlinx.coroutines.flow.Flow

class OfflineAppRepository(
        private val userDao: UserDao
) : AppRepository {
        override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

        override fun getUserStream(username: String): Flow<User?> = userDao.getUser(username)

        override suspend fun getMealsOfUserStream(username: String): List<UserWithMeals> = userDao.getMealsOfUser(username)

        override suspend fun insertMeal(item: Meal) = userDao.insertMeal(item)

        override suspend fun insertUser(user: User) = userDao.insert(user)

        override suspend fun deleteUser(user: User) = userDao.delete(user)

        override suspend fun updateUser(user: User) = userDao.update(user)

        override suspend fun getCurrentFilters(username: String): Int = userDao.getCurrentFilters(username)

        override suspend fun updateFilters(username: String, newfilters: Int) = userDao.updateFilters(username, newfilters)
}