package com.example.myapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue

@Composable
fun WaterCard(
    recipeItem: Recipe,
    onUpdate: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .height(180.dp)
            .width(140.dp)
            .padding(10.dp)
            .clickable(
                onClick = {
                    onUpdate()
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val source = recipeItem.imageUrl
        Image(
            painter = painterResource(id = source),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )

        Text(
            text = "${recipeItem.name}",
            fontSize = 10.sp,
            color = textBlue,
            modifier = Modifier
                .padding(vertical = 20.dp)
        )
    }

}

@Preview(showBackground = false)
@Composable
fun WaterCardPreview() {
    WaterCard(
        Recipe(
            11,
            "Лимонад",
            "Напиток",
            R.drawable.mojito,
            "20 минут",
            listOf("Лайм", "Сахар", "Мята"),
            80,
            listOf(0, 0, 50),
            listOf("+"),
            listOf(
                FilterNames.NO_MEAT.filterName,
                FilterNames.NO_MILK.filterName,
                FilterNames.PREGNANT.filterName,
                FilterNames.LACTATION.filterName
            )
        ),
        onUpdate = { }
    )
}