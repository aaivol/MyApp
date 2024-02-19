package com.example.myapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.ui.filters.FiltersDestination
import com.example.myapp.ui.filters.FiltersScreen
import com.example.myapp.ui.goal.DietGoalDestination
import com.example.myapp.ui.goal.DietGoalScreen
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.home.HomeScreen
import com.example.myapp.ui.login.LoginScreen
import com.example.myapp.ui.login.LoginDestination
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.signup.SignUpScreen

/**
 * Provides Navigation graph for the application.
 */

@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LoginDestination.route
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(navController)
        }
        composable(route = SignUpDestination.route) {
            SignUpScreen(navController)
        }
        composable(route = DietGoalDestination.route) {
            DietGoalScreen(navController)
        }
        composable(route = FiltersDestination.route) {
            FiltersScreen(navController)
        }
        composable(route = HomeDestination.route) {
            HomeScreen(navController)
        }
    }
}