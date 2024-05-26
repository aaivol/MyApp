package com.example.myapp.ui.food

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.DataStoring
import com.example.myapp.R
import com.example.myapp.data.food.Recipe
import com.example.myapp.data.statistics.Meal
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.components.ExpandableCard
import com.example.myapp.ui.components.ExpandableCardPreview
import com.example.myapp.ui.components.Loading
import com.example.myapp.ui.components.RecipeCard
import com.example.myapp.ui.components.showProgress
import com.example.myapp.ui.home.Filters
import com.example.myapp.ui.home.HomeText
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.recipes.RecipeViewModel
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.cruinn_medium
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object FoodDestination : NavigationDestination {
    override val route = "food"
    override val titleRes = R.string.app_name
}

//FOOD SCREEN
@Composable
fun FoodScreen(
    navigateToHome: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    recipeViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
    ) {
        coroutineScope.launch {
            homeViewModel.checkCurrentMeals()
        }
    }
    //get current username from datastore
    val context = LocalContext.current
    val usernameStored = context.dataStore.data.collectAsState(
        initial = DataStoring()
    ).value.user.name

    //update username in viewmodel
    homeViewModel.updateName(usernameStored)
    val userState = homeViewModel.currentUser.value.userDetails

    //filters
    val userFilters = homeViewModel.currentFilters
    FoodBody(
        toHome = {
            navigateToHome()
        },
        homeViewModel,
        recipeViewModel
    )
}

@Composable
fun FoodBody(
    toHome: () -> Unit,
    homeViewModel: HomeViewModel,
    recipeViewModel: RecipeViewModel
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Diagram(
            homeViewModel,
            recipeViewModel
        )
        Cards(
            homeViewModel,
            recipeViewModel)
        OutlinedButton(
            onClick = toHome,
            //connect to viewmodel with filters
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(bottom = 40.dp) // margin
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .padding(10.dp) //padding,
        ) {
            Text(
                text = "Назад",
                color = textBlue,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun Cards(
    homeViewModel: HomeViewModel,
    recipeViewModel: RecipeViewModel
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ExpandableCard(
            "Завтрак",
            homeViewModel = homeViewModel,
            recipes = recipeViewModel.recipes
        )
        ExpandableCard(
            "Обед",
            homeViewModel = homeViewModel,
            recipes = recipeViewModel.recipes
        )
        ExpandableCard(
            "Ужин",
            homeViewModel = homeViewModel,
            recipes = recipeViewModel.recipes
        )
    }
}

@Composable
fun Diagram(
    homeViewModel: HomeViewModel,
    recipeViewModel: RecipeViewModel
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.Blue)
            .fillMaxWidth(0.84f)
            .height(320.dp)
            .drawBehind {
                drawRect(color = Color.White)
            },
        verticalArrangement = Arrangement.Center
    ){
        Column {
            homeViewModel.currentMeals.forEach { 
                writeMeal(
                    meal = it,
                    recipeViewModel.recipes
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun writeMeal(
    meal: Meal,
    recipes: List<Recipe>
){
    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = meal.mealType)
        Column {
            val caloriesNorma = 2000

            val caloriesMeal = caloriesOfRecipe(meal.dishId, recipes) +
                    caloriesOfRecipe(meal.soupId, recipes) +
                    caloriesOfRecipe(meal.saladId, recipes) +
                    caloriesOfRecipe(meal.snackId, recipes)

            val pfcMeal = (0 until balanceOfRecipe(meal.dishId, recipes).size).map {
                balanceOfRecipe(meal.dishId, recipes)[it] +
                        balanceOfRecipe(meal.soupId, recipes)[it] +
                        balanceOfRecipe(meal.saladId, recipes)[it] +
                        balanceOfRecipe(meal.snackId, recipes)[it]
            }

            showProgress(caloriesMeal)

            Row {
                Text(text = " MEAL PFC ", fontSize = 12.sp)
                pfcMeal.forEach {
                    Text(text = "$it ", fontSize = 12.sp)
                }
            }

        }
    }
}

fun caloriesOfRecipe(id: Int, list: List<Recipe>): Int {
    val recipe = list.find { it.id == id }
    return recipe?.calories ?: 0
}

fun balanceOfRecipe(id: Int, list: List<Recipe>): List<Int> {
    val recipe = list.find { it.id == id }
    return recipe?.pfc ?: listOf<Int>(0, 0, 0)
}



fun calculateGCDForListOfNumbers(numbers: List<Int>): Int {
    require(numbers.isNotEmpty()) { "List must not be empty" }
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        var num1 = result
        var num2 = numbers[i]
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        result = num1
    }
    return result
}