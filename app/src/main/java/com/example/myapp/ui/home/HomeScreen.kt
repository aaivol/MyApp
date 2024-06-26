package com.example.myapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.DataStoring
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.user.User
import com.example.myapp.data.user_filter.FilterNames
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.components.Loading
import com.example.myapp.ui.diet.DietGoalBody
import com.example.myapp.ui.diet.DietViewModel
import com.example.myapp.ui.login.LoginBody
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.signup.toUserDetails
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.cruinn_bold
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

//HOME SCREEN
@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    navigateToFilters: () -> Unit,
    navigateToFood: () -> Unit,
    navigateToWater: () -> Unit,
    navigateToRecipes: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    dietViewModel: DietViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    //get current username from datastore
    val context = LocalContext.current
    val usernameStored = context.dataStore.data.collectAsState(
        initial = DataStoring()
    ).value.user.name

    //update username in viewmodel
    viewModel.updateName(usernameStored)
    val userState = viewModel.currentUser.value.userDetails
    val coroutineScope = rememberCoroutineScope()

    //filters
    val userFilters = viewModel.currentFilters

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeText(usernameStored)
        Column (
        ) {
            UserProps(
                update = {
                    coroutineScope.launch {
                        //get properties from Room database
                        viewModel.getUser()
                        viewModel.checkCurrentFilters()
                    }
                },
                userState,
                dietViewModel,
                userFilters
            )
        }
        HomeMenu(
            toFoodClick = {
                navigateToFood()
            },
            toWaterCLick = {
                navigateToWater()
            },
            toSettingsClick = {
                navigateToFilters()
                coroutineScope.launch {
                    viewModel.getUser()
                    viewModel.checkCurrentFilters()
                }
            },
            toRecipesClick = {
                navigateToRecipes()
            }
        )
    }
}

@Composable
private fun UserProps(
    update: () -> Unit,
    userDetails: UserDetails,
    dietVM: DietViewModel,
    userFilters: List<String>
) {
    update()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (userDetails.dietId.isNotBlank()) {
                val diet = dietVM.getDietProps(userDetails.dietId.toInt())
                DietText(diet)
            }
        }

        when {
            userDetails.username == null -> Loading()
            else -> Row {
                userFilters.forEach{
                    Filters(name = it)
                }

            }
        }

    }
}

@Composable
fun DietText(currentDiet: Diet) {
    Text(
        text = currentDiet.quote,
        fontSize = 18.sp,
        color = textBlue,
        modifier = Modifier
            .padding(top = 0.dp) //margin
            .fillMaxWidth()
            .background(page)
            .height(80.dp)
            .padding(horizontal = 40.dp) //padding
    )
}


@Composable
fun HomeText(username: String) {
    Text("Приветствую, $username !",
        fontSize = 24.sp,
        color = textBlue,
        modifier = Modifier
            .padding(top = 40.dp) //margin
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 40.dp) //padding
    )
}

@Composable
fun Filters(name: String){
    Text(" $name ",
        fontSize = 14.sp,
        color = textBlue,
        modifier = Modifier
            .height(40.dp)
            .padding(10.dp) //padding
    )
}

@Composable
fun HomeMenu(
    toFoodClick: () -> Unit,
    toWaterCLick: () -> Unit,
    toSettingsClick: () -> Unit,
    toRecipesClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = toFoodClick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 10.dp) // margin
                .fillMaxWidth(0.85f)
                .height(140.dp)
                .padding(16.dp) //margin
        ) {
            Text(
                "Питание",
                color = textBlue,
                fontFamily = cruinn_bold,
                fontSize = 22.sp
            )
        }

        OutlinedButton(
            onClick = toWaterCLick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = login,
            ),
            modifier = Modifier
                .padding(top = 0.dp) // margin
                .fillMaxWidth(0.85f)
                .height(140.dp)
                .padding(16.dp) //margin
        ) {
            Text(
                "Вода",
                color = textBlue,
                fontFamily = cruinn_bold,
                fontSize = 22.sp
            )
        }

        OutlinedButton(
            onClick = toRecipesClick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 0.dp) // margin
                .fillMaxWidth(0.85f)
                .height(110.dp)
                .padding(16.dp) //margin
        ) {
            Text(
                "Рецепты для вас",
                color = textBlue,
                fontFamily = cruinn_bold,
                fontSize = 22.sp
            )
        }

        OutlinedButton(
            onClick = toSettingsClick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = page,
            ),
            modifier = Modifier
                .padding(top = 0.dp) // margin
                .fillMaxWidth(0.85f)
                .height(100.dp)
                .padding(16.dp) //margin
        ) {
            Text(
                "Настройки",
                color = textBlue,
                fontFamily = cruinn_bold,
                fontSize = 22.sp
            )
        }
    }

}