package com.example.myapp.ui.water


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.DataStoring
import com.example.myapp.data.diet.Diet
import com.example.myapp.data.user.User
import com.example.myapp.data.user_filter.FilterNames
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.components.Loading
import com.example.myapp.ui.components.WaterCard
import com.example.myapp.ui.components.waterDiagram
import com.example.myapp.ui.diet.DietGoalBody
import com.example.myapp.ui.diet.DietViewModel
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.login.LoginBody
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.recipes.RecipeViewModel
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

object WaterDestination : NavigationDestination {
    override val route = "water"
    override val titleRes = R.string.app_name
}

//WATER SCREEN
@SuppressLint("UnrememberedMutableState")
@Composable
fun WaterScreen(
    navigateToHome: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    recipeViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory),
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
                recipeViewModel,
                userFilters
            )
        }
        WaterBody(
            BackClick = {
                navigateToHome()
            },
            recipeViewModel
        )
    }
}

@Composable
private fun UserProps(
    update: () -> Unit,
    userDetails: UserDetails,
    recipeVM: RecipeViewModel,
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
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun WaterBody(
    BackClick: () -> Unit,
    recipeVM: RecipeViewModel
) {
    var updateDiagram by remember {  mutableStateOf(false) }
    var drinked by remember {  mutableStateOf(0) }
    var calories by remember {  mutableStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Дневная норма (ml)",
            fontSize = 24.sp,
            color = textBlue,
            modifier = Modifier
                .padding(top = 40.dp) //margin
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp) //padding
        )

        if (updateDiagram){
            if (drinked < 2000){
                drinked += 200
            }
            waterDiagram(drinked)
            updateDiagram = false
        }
        else {
            waterDiagram(drinked)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Калории",
                fontSize = 24.sp,
                color = textBlue,
                modifier = Modifier
                    .padding(top = 40.dp) //margin
                    .height(50.dp)
                    .padding(horizontal = 40.dp) //padding
            )
            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .height(36.dp)
                    .width(54.dp)
                    .padding(2.dp)
                    .border(2.dp, textBlue, RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = calories.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = textBlue,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {

            Row {
                recipeVM.recipes.forEach { it
                    if (it.type == "Напиток" && it.id < 12 ){
                        WaterCard(
                            it,
                            onUpdate = {
                                updateDiagram = true
                                calories += it.calories
                            }
                        )
                    }
                }
            }
            Row {
                recipeVM.recipes.forEach { it
                    if (it.type == "Напиток" && it.id > 11 ){
                        WaterCard(
                            it,
                            onUpdate = {
                                updateDiagram = true
                                calories += it.calories
                            }
                        )
                    }
                }
            }
        }

        OutlinedButton(
            onClick = BackClick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 0.dp) // margin
                .fillMaxWidth(0.85f)
                .height(100.dp)
                .padding(16.dp) //margin
        ) {
            Text(
                "Назад",
                color = textBlue,
                fontFamily = cruinn_bold,
                fontSize = 22.sp
            )
        }
    }

}