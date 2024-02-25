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
fun MyAppNavHost(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = LoginDestination.route
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(
                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = SignUpDestination.route) {
            SignUpScreen(
                navigateToDietGoal = { navController.navigate(DietGoalDestination.route) }
            )
        }
        composable(route = DietGoalDestination.route) {
            DietGoalScreen(
                navigateToFilters = { navController.navigate(FiltersDestination.route) }
            )
        }
        composable(route = FiltersDestination.route) {
            FiltersScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = HomeDestination.route) {
            HomeScreen(navController)
        }
    }
}