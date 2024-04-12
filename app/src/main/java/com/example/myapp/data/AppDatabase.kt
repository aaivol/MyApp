package com.example.myapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.diet.DietDao
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import com.example.myapp.data.user_filter.UserFilter
import com.example.myapp.data.user_filter.UserFilterDao

@Database(
    entities = [
        User::class,
        Diet::class,
        UserFilter::class
               ],
    version = 5,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun dietDao(): DietDao
    abstract fun userFilterDao(): UserFilterDao

    companion object{
        @Volatile
        private var Instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "my_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}