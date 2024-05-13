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

        override suspend fun setDish(username: String, mealType: String, dishId: Int) = userDao.setDish(username, mealType, dishId)

        override suspend fun setSoup(username: String, mealType: String, soupId: Int) = userDao.setSoup(username, mealType, soupId)

        override suspend fun setSalad(username: String, mealType: String, saladId: Int) = userDao.setSalad(username, mealType, saladId)

        override suspend fun setSnack(username: String, mealType: String, snackId: Int) = userDao.setSnack(username, mealType, snackId)

        override suspend fun insertMeal(item: Meal) = userDao.insertMeal(item)

        override suspend fun insertUser(user: User) = userDao.insert(user)

        override suspend fun deleteUser(user: User) = userDao.delete(user)

        override suspend fun updateUser(user: User) = userDao.update(user)

        override suspend fun getCurrentFilters(username: String): Int = userDao.getCurrentFilters(username)

        override suspend fun updateFilters(username: String, newfilters: Int) = userDao.updateFilters(username, newfilters)
}