package com.example.myapp.ui.recipes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.DataStoring
import com.example.myapp.R
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.components.Loading
import com.example.myapp.ui.components.RecipeCard
import com.example.myapp.ui.diet.DietViewModel
import com.example.myapp.ui.filters.FiltersViewModel
import com.example.myapp.ui.home.Filters
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.cruinn_medium
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch

object RecipeDestination : NavigationDestination {
    override val route = "recipes"
    override val titleRes = R.string.app_name
}

//RECIPE SCREEN
@Composable
fun RecipeScreen(
    navigateToHome: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    recipeViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    //get current username from datastore
    val context = LocalContext.current
    val usernameStored = context.dataStore.data.collectAsState(
        initial = DataStoring()
    ).value.user.name

    //update username in viewmodel
    homeViewModel.updateName(usernameStored)
    val userState = homeViewModel.currentUser.value.userDetails
    val coroutineScope = rememberCoroutineScope()

    //filters
    val userFilters = homeViewModel.currentFilters
    RecipeBody(
        updateState = {
            coroutineScope.launch {
                //get properties from Room database
                homeViewModel.getUser()
                homeViewModel.checkCurrentFilters()
            }
        },
        toHome = {
            navigateToHome()
        },
        userState,
        recipeViewModel,
        homeViewModel
    )
}

@Composable
fun RecipeBody(
    updateState: () -> Unit,
    toHome: () -> Unit,
    userDetails: UserDetails,
    recipeViewModel: RecipeViewModel,
    homeViewModel: HomeViewModel
) {
    updateState()

    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RecipeTop()

        Column(
            modifier = Modifier
                .background(page)
                .padding(vertical = 20.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when {
                userDetails.username == null -> Loading()
                else -> Column {
                    recipeViewModel.recipes.forEach {
                        if (it.compareFilters(homeViewModel.currentFilters)){
                            RecipeCard(recipeItem = it)
                        }
                    }

                }
            }

        }

        OutlinedButton(
            onClick = toHome,
            //connect to viewmodel with filters
            border = BorderStroke(1.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 20.dp) // margin
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .padding(10.dp) //padding
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
fun RecipeTop() {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .background(login),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "РЕЦЕПТЫ",
            fontSize = 35.sp,
            fontFamily = cruinn_medium,
            style = TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(textBlue, login),
                    tileMode = TileMode.Mirror
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp) //padding
                .padding(horizontal = 50.dp) //padding
        )
    }
}