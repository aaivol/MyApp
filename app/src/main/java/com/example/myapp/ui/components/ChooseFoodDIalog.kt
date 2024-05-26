package com.example.myapp.ui.components

import android.app.AlertDialog
import android.widget.ToggleButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.data.food.Recipe
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.recipes.RecipeViewModel
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch

@Composable
fun ChooseFoodDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    chosenType: String,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    mealType: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = page,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val coroutineScope = rememberCoroutineScope()

                var currentMealType by remember {
                    mutableStateOf("")
                }

                when (mealType) {
                    "Завтрак" -> {
                        currentMealType = "breakfast"
                    }
                    "Обед" -> {
                        currentMealType = "lunch"
                    }
                    "Ужин" -> {
                        currentMealType = "dinner"
                    }
                }

                val recipeViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory)
                var filteredMeals by remember { mutableStateOf(emptyList<Recipe>()) }
                filteredMeals = recipeViewModel.recipes.filter { it.type == chosenType }

                //CLICK TO CHOOSE MEAL
                var selectedMeal by remember {
                    mutableStateOf(filteredMeals.first())
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                        .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //FILTER MEALS BY TYPE


                    filteredMeals.forEach { meal ->
                        var borderColor = if (selectedMeal == meal) textAccent else Color.White

                        RecipeCardForDialog(
                            meal,
                            onSelectionChange = {
                                selectedMeal = meal
                            },
                            borderColor
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(15.dp),
                    ) {
                        Text("Назад")
                    }
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                homeViewModel.updateMeal(selectedMeal, currentMealType)
                                homeViewModel.checkCurrentMeals()
                            }
                            onConfirmation() },
                        modifier = Modifier.padding(15.dp),
                    ) {
                        Text("Далее")
                    }
                }
            }
        }
    }
}