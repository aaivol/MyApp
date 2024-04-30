package com.example.myapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.DataStoring
import com.example.myapp.R
import com.example.myapp.data.food.Recipe
import com.example.myapp.data.user_filter.FilterNames
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.diet.DietGoalButtons
import com.example.myapp.ui.diet.DietGoalText
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.recipes.RecipeViewModel
import com.example.myapp.ui.signup.UserDetails
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.launch

//RECIPE CARD
@Composable
fun RecipeCard(
    recipeItem: Recipe
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .height(160.dp)
            .fillMaxWidth(0.9f)
            .padding(vertical = 20.dp),
    ){
        val source = recipeItem.imageUrl
        Image(
            painter = painterResource(id = source),
            contentDescription = "",
            modifier = Modifier
                .width(130.dp)
                .fillMaxHeight(0.75f)
                .padding(horizontal = 15.dp)
        )

        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "${recipeItem.name}",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
            Text(
                text = "${recipeItem.time}",
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp)
            )
            Text(
                text = "${recipeItem.calories} кк",
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp)
            )
        }

    }

}

@Preview(showBackground = false)
@Composable
fun RecipeCardPreview() {
    RecipeCard(
        Recipe(
            1,
            "Карбонара",
            "Основное",
            0,
            "30 минут",
            listOf("Ветчина", "Яйцо", "Сливки 20%", "Чеснок", "Паста"),
            275,
            listOf(11, 15, 27),
            listOf("мяу", "миу"),
            listOf(
                FilterNames.ALLERGY.toString(),
                FilterNames.PREGNANT.toString(),
                FilterNames.LACTATION.toString()
            )
        )
    )
}
