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
import com.example.myapp.ui.recipes.RecipeViewModel
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue

@Composable
fun ChooseFoodDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    chosenType: String
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

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //RECIPES 2
                    val recipeViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory)
                    var filteredDishes by remember { mutableStateOf(emptyList<Recipe>()) }

                    filteredDishes = recipeViewModel.recipes.filter { it.type == chosenType }

                    repeat (filteredDishes.size / 2) { i ->
                        Row(
                            modifier = Modifier.fillMaxWidth() // maybe smth else here
                        ) {
                            RecipeCardForDialog(filteredDishes[i * 2])
                            RecipeCardForDialog(filteredDishes[i * 2 + 1])
                        }
                    }

                    if (filteredDishes.size % 2 == 1) {
                        RecipeCardForDialog(filteredDishes.last())
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
                            onClick = { onConfirmation() },
                            modifier = Modifier.padding(15.dp),
                        ) {
                            Text("Далее")
                        }
                    }
                }
            }
        }
    }
}