package com.example.myapp.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.myapp.MainActivity
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpDestination
import com.example.myapp.ui.signup.SignUpViewModel
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch


object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.app_name
    const val userNameArg = "UiUsername"
    const val userPasswordArg = "UiPassword"
}


//LOGIN SCREEN
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginBody(
            userUiState = viewModel.userUiState,
            onUserValueChange = viewModel::updateUiState,
            HomeClick = {
                coroutineScope.launch {
                    val checkUser = viewModel.userUiState.userDetails
                    if (viewModel.tryLogin(checkUser)) {
                        navController.navigate(HomeDestination.route)
                    }
                    else {
                        navController.navigate(SignUpDestination.route)
                    }
                }
            },
            SignUpClick = {
                navController.navigate(SignUpDestination.route)
            }
        )
    }

}


@Composable
fun LoginBody(
    userUiState: UserUiState,
    onUserValueChange: (UserDetails) -> Unit,
    HomeClick: () -> Unit,
    SignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginText()
        Column() {
            InputUserData(
                userDetails = userUiState.userDetails,
                onValueChange = onUserValueChange,
            )
            OutlinedButton(
                onClick = HomeClick,
                border = BorderStroke(2.dp, borderBlue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .padding(top = 50.dp) // margin
                    .fillMaxWidth(0.8f)
                    .height(110.dp)
                    .padding(20.dp) //margin
            ) {
                Text(
                    "Вперед!",
                    color = textBlue,
                    fontSize = 20.sp
                )
            }

            TextButton(
                onClick = SignUpClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    "Еще нет аккаунта? Создать",
                    color = textAccent,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputUserData(
    userDetails: UserDetails,
    onValueChange: (UserDetails) -> Unit = {},
) {
    Column (
        modifier = Modifier
            .padding(top = 50.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InputField(
            userDetails = userDetails,
            onValueChange = onValueChange
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    userDetails: UserDetails,
    onValueChange: (UserDetails) -> Unit = {}
) {
    TextField(
        value = userDetails.username,
        onValueChange = { onValueChange(userDetails.copy(username = it)) },
        label = { Text(
            "Введите никнейм",
            fontSize = 18.sp,
            color = textBlue
        ) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .padding(top = 10.dp) // margin
            .fillMaxWidth(0.8f)
            .height(110.dp)
            .padding(20.dp) //padding
            .border(2.dp, borderBlue, RoundedCornerShape(60.dp))
    )

    TextField(
        value = userDetails.password,
        onValueChange = { onValueChange(userDetails.copy(password = it)) },
        label = { Text(
            "Введите пароль",
            fontSize = 18.sp,
            color = textBlue
        ) },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier
            .padding(top = 10.dp) // margin
            .fillMaxWidth(0.8f)
            .height(110.dp)
            .padding(20.dp) //padding
            .border(2.dp, borderBlue, RoundedCornerShape(60.dp))
    )
}

@Composable
fun LoginText() {
    Text("LOGIN",
        fontSize = 35.sp,
        color = textBlue,
        modifier = Modifier
            .padding(0.dp) //margin
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(login)
            .padding(horizontal = 60.dp, vertical = 50.dp) //padding
    )
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginBody(
        userUiState = UserUiState(
            UserDetails(
                username = "pivk", password = "1234"
            )
        ), onUserValueChange = {}, HomeClick = {}, SignUpClick = {}
    )
}