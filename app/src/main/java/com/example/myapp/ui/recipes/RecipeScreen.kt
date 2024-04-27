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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.cruinn_medium
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue

object RecipeDestination : NavigationDestination {
    override val route = "recipes"
    override val titleRes = R.string.app_name
}

//RECIPE SCREEN
@Composable
fun RecipeScreen(
    navigateToHome: () -> Unit
) {
    RecipeBody(
        toHome = {
            navigateToHome()
        }
    )
}

@Composable
fun RecipeBody(
    toHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RecipeTop()
        OutlinedButton(
            onClick = toHome,
            //connect to viewmodel with filters
            border = BorderStroke(1.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 450.dp) // margin
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

@Preview(showBackground = true)
@Composable
fun RecipeBodyPreview() {
    RecipeBody(
        toHome = {}
    )
}