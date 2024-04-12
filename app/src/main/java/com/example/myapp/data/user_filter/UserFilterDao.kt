package com.example.myapp.data.user_filter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.relations.DietWithUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface UserFilterDao {
    @Update
    suspend fun update(filter: UserFilter)

    @Transaction
    @Query("SELECT * FROM user_filter WHERE userId = :userId")
    fun getCurrentUserFilter(userId: Int): UserFilter
}