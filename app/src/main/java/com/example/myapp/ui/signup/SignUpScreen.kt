package com.example.myapp.ui.signup

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.myapp.ui.goal.DietGoalDestination
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue


object SignUpDestination : NavigationDestination {
    override val route = "signup"
    override val titleRes = R.string.app_name
}


//SIGN UP SCREEN
@Composable
fun SignUpScreen(
    navController: NavController
    //viewmodel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpText()
        Column (
            modifier = Modifier
                .padding(top = 50.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Input("никнейм")
            Input("пароль")

            OutlinedButton(
                onClick = {
                      navController.navigate(DietGoalDestination.route)
                },
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
                    "Создать аккаунт!",
                    color = textBlue,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun SignUpBody(
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignUpText()
        Column (
            modifier = Modifier
                .padding(top = 50.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Input("никнейм")
            Input("пароль")

            OutlinedButton(
                onClick = {},
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
                    "Создать аккаунт!",
                    color = textBlue,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(message: String) {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(
            "Придумайте $message",
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
fun SignUpText() {
    Text("Регистрация",
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
fun SignUpBodyPreview() {
    SignUpBody()
}