package com.example.myapp.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val appRepository: AppRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [AppRepository]
     */
    override val appRepository: AppRepository by lazy {
        OfflineAppRepository(
            AppDatabase.getDatabase(context).userDao(),
            AppDatabase.getDatabase(context).dietDao(),
            AppDatabase.getDatabase(context).userFilterDao()
        )
    }
}