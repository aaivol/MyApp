package com.example.myapp.ui.home

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.data.user.User
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue


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
    val homeUiState by viewModel.homeUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeText("kotik")
        Column (
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserItemList(
                userList = homeUiState.userList
            )
        }
    }
}

@Composable
private fun UserItemList(
    userList: List<User>
){
    LazyColumn(
    ) {
        items(items = userList, key = { it.id }) { user ->
            UserItem(user = user)
        }
    }
}


@Composable
private fun UserItem(
    user: User
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(page),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeText(user.username)
        Column (
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "# " + user.username,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(Color.Magenta)
                    .height(50.dp)
                    .padding(10.dp) //padding
            )
            Text(
                text = "# " + user.password,
                fontSize = 24.sp,
                color = textBlue,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(page)
                    .height(50.dp)
                    .padding(10.dp) //padding
            )
            Text(
                text = "# " + user.dietKey.toString(),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(0.dp) //margin
                    .fillMaxWidth()
                    .background(Color.Magenta)
                    .height(50.dp)
                    .padding(10.dp) //padding
            )
        }
    }
}

@Composable
fun HomeText(username: String) {
    Text("Здравствуй, $username !",
        fontSize = 22.sp,
        color = textBlue,
        modifier = Modifier
            .padding(0.dp) //margin
            .fillMaxWidth()
            .height(60.dp)
            .padding(top = 30.dp) //padding
            .padding(horizontal = 40.dp) //padding
    )
}


@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    UserItem(
        User(1, "meow", "murr", 1)
    )
}