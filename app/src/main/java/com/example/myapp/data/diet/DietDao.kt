package com.example.myapp.data.diet

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapp.data.relations.DietWithUsers
import com.example.myapp.data.user.User
import kotlinx.coroutines.flow.Flow

@Dao
interface DietDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diet: Diet)

    @Update
    suspend fun update(diet: Diet)

    @Delete
    suspend fun delete(diet: Diet)

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUser(user: User)

    @Transaction
    @Query("SELECT * FROM diets WHERE id = :dietId")
    suspend fun getDietWithUsers(dietId: Int): List<DietWithUsers>
}