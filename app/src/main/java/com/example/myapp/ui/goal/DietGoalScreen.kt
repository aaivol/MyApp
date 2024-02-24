package com.example.myapp.ui.goal

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.filters.FiltersDestination
import com.example.myapp.ui.filters.FiltersScreen
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpViewModel
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch


object DietGoalDestination : NavigationDestination {
    override val route = "diets"
    override val titleRes = R.string.app_name
}

//CHOOSE DIET GOAL SCREEN
@Composable
fun DietGoalScreen(
    navController: NavController,
    viewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    DietGoalBody(
        userUiState = viewModel.userUiState,
        toHomeClick = {
            coroutineScope.launch {
                viewModel.updateUser()
                navController.navigate(HomeDestination.route)
            }
        }
    )
}

@Composable
fun DietGoalBody(
    userUiState: UserUiState,
    toHomeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DietGoalText()
        DietGoalButtons(
            userDetails = userUiState.userDetails
        )

        OutlinedButton(
            onClick = toHomeClick,
            border = BorderStroke(1.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 100.dp) // margin
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .padding(10.dp) //padding
        ) {
            Text(
                text = "Далее",
                color = textBlue,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun DietGoalText() {
    Text("Какая у вас цель?",
        fontSize = 20.sp,
        color = textBlue,
        modifier = Modifier
            .padding(0.dp) //margin
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 40.dp, vertical = 50.dp) //padding
    )
}

@Composable
fun DietGoalButtons(
    userDetails: UserDetails
) {
    val diets = listOf(
        "Похудеть",
        "Набрать вес",
        "Нарастить мышцы",
        "Поддерживать форму",
        "Просто следить за питанием",
    )
    var selectedDiet by remember {
        mutableStateOf(diets.first())
    }
    val onSelectionChange = { diet: String ->
        selectedDiet = diet
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp),
    ) {
        diets.forEach { diet ->
            val color = if (selectedDiet == diet) orange else login

            //KEY for DATABASE
            val key: Int = diets.indexOf(diet) + 1

            OutlinedButton(
                onClick = {
                    onSelectionChange(diet)
                    userDetails.dietKey = key.toString()
                },
                border = BorderStroke(1.dp, borderBlue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                ),
                modifier = Modifier
                    .padding(0.dp) // margin
                    .fillMaxWidth(0.9f)
                    .height(80.dp)
                    .padding(8.dp)
            ) {
                Text(
                    diet + key.toString(),
                    color = textBlue,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DietGoalBodyPreview() {
    DietGoalBody(
        userUiState = UserUiState(
            UserDetails(
                username = "pivk1", password = "1234", dietKey = "1"
            )
        ),
        toHomeClick = {}
    )
}
