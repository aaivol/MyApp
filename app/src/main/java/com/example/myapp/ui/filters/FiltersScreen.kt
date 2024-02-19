package com.example.myapp.ui.filters

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue

object FiltersDestination : NavigationDestination {
    override val route = "filters"
    override val titleRes = R.string.app_name
}


//FILTERS SCREEN
@Composable
fun FiltersScreen(
    navController: NavController
    //viewmodel
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FiltersText()
        DietGoalButtons()
        OutlinedButton(
            onClick = {
                //navController.navigate(HomeDestination.route)
            },
            border = BorderStroke(1.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 10.dp) // margin
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
fun FiltersBody(
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FiltersText()
        DietGoalButtons()
        OutlinedButton(
            onClick = {
                //navigate-to-home
            },
            border = BorderStroke(1.dp, borderBlue),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
            ),
            modifier = Modifier
                .padding(top = 10.dp) // margin
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
fun FiltersText() {
    Text("Особенности, которые важны",
        fontSize = 20.sp,
        color = textBlue,
        modifier = Modifier
            .padding(0.dp) //margin
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 40.dp)
            .padding(top = 50.dp) //padding
    )
}
@Composable
fun DietGoalButtons() {
    val filters = listOf(
        "Диабет",
        "Аллергия",
        "Ожирение",
        "Гастрит",
        "Без мяса",
        "Без молока",
        "Веган",
        "Жду ребенка",
        "Кормление",
    )

    var selectedFilters by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { filter: String ->
        selectedFilters = filter
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp),
    ) {
        filters.forEach { filter ->
            val color = if (selectedFilters == filter) orange else login
            val len = filter.length
            val btnwid = (len * 24).dp

            //KEY for DATABASE
            val key: Int = filters.indexOf(filter) + 1

            OutlinedButton(
                onClick = {
                    onSelectionChange(filter)
                    //viewmodel-send-key
                },
                border = BorderStroke(1.dp, borderBlue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                ),
                modifier = Modifier
                    .padding(0.dp) // margin
                    .width(btnwid)
                    .height(58.dp)
                    .padding(6.dp)
            ) {
                Text(
                    filter,
                    color = textBlue,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FiltersBodyPreview() {
    FiltersBody()
}