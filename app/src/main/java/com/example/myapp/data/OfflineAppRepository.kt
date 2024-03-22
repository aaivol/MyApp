package com.example.myapp.data

import com.example.myapp.data.diet.Diet
import com.example.myapp.data.diet.DietDao
import com.example.myapp.data.relations.DietWithUsers
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import kotlinx.coroutines.flow.Flow

class OfflineAppRepository(
        private val userDao: UserDao,
        private val dietDao: DietDao
) : AppRepository {
        override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

        override fun getUserStream(username: String): Flow<User?> = userDao.getUser(username)

        override suspend fun updateDietOfUser(username: String, dietId: Int) = userDao.updateDietOfUser(username, dietId)

        override suspend fun insertUser(user: User) = userDao.insert(user)

        override suspend fun deleteUser(user: User) = userDao.delete(user)

        override suspend fun updateUser(user: User) = userDao.update(user)

        override fun getDietWithUsersStream(dietId: Int): Flow<List<DietWithUsers>> = dietDao.getDietWithUsers(dietId)

        override suspend fun insertDiet(item: Diet) = dietDao.insert(item)

        override suspend fun deleteDiet(item: Diet) = dietDao.delete(item)

        override suspend fun updateDiet(item: Diet) = dietDao.update(item)
}