package com.example.myapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import com.example.myapp.data.food.Recipe
import com.example.myapp.data.user_filter.FilterNames

@Composable
fun RecipeCardForDialog(
    recipeItem: Recipe
) {
    Column(
        modifier = Modifier
        .padding(10.dp)
        .background(Color.White, RoundedCornerShape(10.dp))
        .height(100.dp)
        .width(100.dp)
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val source = recipeItem.imageUrl
        Image(
            painter = painterResource(id = source),
            contentDescription = "",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
        )

        Text(
            text = "${recipeItem.name}",
            fontSize = 9.sp,
            modifier = Modifier
        )
    }

}

@Preview(showBackground = false)
@Composable
fun RecipeCardDialogPreview() {
    RecipeCardForDialog(
        Recipe(
            1,
            "Карбонара",
            "Основное",
            R.drawable.carbonara,
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