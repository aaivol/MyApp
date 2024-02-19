package com.example.myapp

import android.app.Application
import com.example.myapp.data.AppContainer
import com.example.myapp.data.AppDataContainer

class Dependencies : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}