package com.example.myapp.ui.components

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.R
import com.example.myapp.data.food.Recipe
import com.example.myapp.data.statistics.Meal
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.food.FoodScreen
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.textBlue

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    title: String,
    titleFontWeight: FontWeight = FontWeight.Bold,
    padding: Dp = 12.dp,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    recipes: List<Recipe>
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = orange,
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        val openAlertDialog = remember { mutableStateOf(false) }

        // ...
        when {
            // ...
            openAlertDialog.value -> {
                CategoryDialog(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        },
                    homeViewModel,
                    title
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(10.dp),
                    text = title,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                var mealToShow: Meal = Meal(0,"", "",0,0,0,0)

                when (title) {
                    "Завтрак" -> {
                        mealToShow = homeViewModel.currentMeals.filter { it.mealType.equals("breakfast") }.first()
                    }
                    "Обед" -> {
                        mealToShow = homeViewModel.currentMeals.filter { it.mealType.equals("lunch") }.first()
                    }
                    "Ужин" -> {
                        mealToShow = homeViewModel.currentMeals.filter { it.mealType.equals("dinner") }.first()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (mealToShow.dishId != 0){
                        Image(
                            painter = painterResource(
                                id = pictureOfRecipe(mealToShow.dishId, recipes
                                )),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .width(80.dp)
                                .height(80.dp)
                                .padding(10.dp) //padding
                        )
                    }
                    if (mealToShow.soupId != 0){
                        Image(
                            painter = painterResource(
                                id = pictureOfRecipe(mealToShow.soupId, recipes
                                )),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .width(80.dp)
                                .height(80.dp)
                                .padding(10.dp) //padding
                        )
                    }
                    if (mealToShow.saladId != 0){
                        Image(
                            painter = painterResource(
                                id = pictureOfRecipe(mealToShow.saladId, recipes
                                )),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .width(80.dp)
                                .height(80.dp)
                                .padding(10.dp) //padding
                        )
                    }
                    if (mealToShow.snackId != 0){
                        Image(
                            painter = painterResource(
                                id = pictureOfRecipe(mealToShow.snackId, recipes
                                )),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .width(80.dp)
                                .height(80.dp)
                                .padding(10.dp) //padding
                        )
                    }

                    if (mealToShow.dishId * mealToShow.saladId * mealToShow.soupId * mealToShow.snackId == 0){
                        OutlinedButton(
                            onClick = { openAlertDialog.value = true },
                            border = BorderStroke(1.dp, borderBlue),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                            ),
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .width(120.dp)
                                .height(140.dp)
                                .padding(10.dp) //padding
                        ) {
                            Text(
                                text = "+",
                                color = textBlue,
                                fontSize = 26.sp
                            )
                        }
                    }

                }
            }
        }
    }
}

fun pictureOfRecipe(id: Int, list: List<Recipe>): Int {
    val recipe = list.find { it.id == id }
    return recipe?.imageUrl ?: 0
}

@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "My Title", recipes = emptyList()
    )
}