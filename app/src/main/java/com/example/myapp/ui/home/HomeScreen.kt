package com.example.myapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.myapp.data.user.User
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.toUserDetails
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
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
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val context = LocalContext.current

    val usernameStored = context.dataStore.data.collectAsState(
        initial = DataStoring()
    ).value.user.name.toString()

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeText(usernameStored)
        Column (
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserUpdates(
                getProps = {
                   coroutineScope.launch {
                       viewModel.updateName(usernameStored)
                   }
                },
                viewModel
            )
        }
    }
}

@Composable
private fun UserUpdates(
    getProps: () -> Unit,
    viewModel: HomeViewModel
) {
    val currentUser by viewModel.uiState.collectAsState()
    UserItem(currentUser.userDetails)
}

@Composable
private fun UserItem(
    userDetails: UserDetails
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column (
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "ID # " + userDetails.id,
                fontSize = 18.sp,
                color = textBlue,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(page)
                    .height(45.dp)
                    .padding(10.dp) //padding
            )

            Text(
                text = "name # " + userDetails.username,
                fontSize = 18.sp,
                color = textBlue,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(page)
                    .height(45.dp)
                    .padding(10.dp) //padding
            )

            Text(
                text = "diet # " + userDetails.dietKey.toString(),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(login)
                    .height(40.dp)
                    .padding(10.dp) //padding
            )
        }
    }
}

@Composable
fun HomeText(username: String) {
    Text("Здравствуй, $username !",
        fontSize = 24.sp,
        color = textBlue,
        modifier = Modifier
            .padding(0.dp) //margin
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 30.dp) //padding
            .padding(horizontal = 40.dp) //padding
    )
}


@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    UserItem(
        User(1, "meow", "murr", 1).toUserDetails()
    )
}