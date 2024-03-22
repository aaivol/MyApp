package com.example.myapp.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.login.LoginBody
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.signup.SignUpViewModel
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.signup.UserUiState
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.cruinn_black
import com.example.myapp.ui.theme.cruinn_bold
import com.example.myapp.ui.theme.cruinn_medium
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch

object SettingsDestination : NavigationDestination {
    override val route = "settings"
    override val titleRes = R.string.app_name
}

//SETTINGS SCREEN
@Composable
fun SettingsScreen(
    navigateToHome: () -> Unit
    //viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SettingsBody(
            HomeClick = {
                navigateToHome()
            }
        )
    }

}

@Composable
fun SettingsBody(
    HomeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginTop()
        OutlinedButton(
            onClick = HomeClick,
            border = BorderStroke(2.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 450.dp) // margin
                .fillMaxWidth(0.8f)
                .height(110.dp)
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

@Composable
fun LoginTop() {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .background(login),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "НАСТРОЙКИ",
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


@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsBody({})
}