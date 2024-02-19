package com.example.myapp.data

import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import kotlinx.coroutines.flow.Flow

class OfflineAppRepository(private val userDao: UserDao) : AppRepository {
        override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

        override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)

        override suspend fun insertUser(user: User) = userDao.insert(user)

        override suspend fun deleteUser(user: User) = userDao.delete(user)

        override suspend fun updateUser(user: User) = userDao.update(user)
}