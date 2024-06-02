package com.example.myapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapp.ui.filters.FiltersDestination
import com.example.myapp.ui.filters.FiltersScreen
import com.example.myapp.ui.diet.DietGoalDestination
import com.example.myapp.ui.diet.DietGoalScreen
import com.example.myapp.ui.food.FoodDestination
import com.example.myapp.ui.food.FoodScreen
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.home.HomeScreen
import com.example.myapp.ui.login.LoginScreen
import com.example.myapp.ui.login.LoginDestination
import com.example.myapp.ui.recipes.RecipeDestination
import com.example.myapp.ui.recipes.RecipeScreen
import com.example.myapp.ui.settings.SettingsDestination
import com.example.myapp.ui.settings.SettingsScreen
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.signup.SignUpScreen
import com.example.myapp.ui.water.WaterDestination
import com.example.myapp.ui.water.WaterScreen

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
            HomeScreen(
                navigateToSettings = { navController.navigate(SettingsDestination.route) },
                navigateToFood = { navController.navigate(FoodDestination.route) },
                navigateToWater = { navController.navigate(WaterDestination.route) },
                navigateToRecipes = { navController.navigate(RecipeDestination.route) }
            )
        }
        composable(route = SettingsDestination.route) {
            SettingsScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = FoodDestination.route) {
            FoodScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = WaterDestination.route) {
            WaterScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = RecipeDestination.route) {
            RecipeScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
            )
        }
    }
}