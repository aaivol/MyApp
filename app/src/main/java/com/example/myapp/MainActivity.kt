package com.example.myapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.myapp.data.AppDatabase
import com.example.myapp.data.AppRepository
import com.example.myapp.data.diet.Diet
import com.example.myapp.ui.diet.DietViewModel
import com.example.myapp.ui.navigation.MyAppNavHost
import com.example.myapp.ui.theme.MyAppTheme
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("data-storing.json", DataStoringSerializer)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}