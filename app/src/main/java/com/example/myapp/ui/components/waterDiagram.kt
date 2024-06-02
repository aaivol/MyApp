package com.example.myapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun waterDiagram(
    ml : Int = 500,
){

    val gradient = Brush.linearGradient(listOf(
        orange,
        Color.Cyan))

    val gradientDanger = Brush.linearGradient(listOf(
        orange,
        Color.Red))

    val progressFactor by remember(ml) {
        mutableStateOf(ml*0.0005f)
    }

    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(0.8f).height(46.dp).border(
            width = 2.dp,
            color = textBlue,
            shape = RoundedCornerShape(50.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomEndPercent = 50,
                bottomStartPercent = 50
            )
        )
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var thebrush = gradient

        if (progressFactor > 500){
            thebrush = gradientDanger
        }

        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = { },
            modifier = Modifier
                .fillMaxWidth(progressFactor)
                .background(thebrush),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )) {

            Text(text = (ml).toString(),
                fontSize = 16.sp,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(26.dp))
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
                    .padding(1.dp),
                color = textBlue,
                textAlign = TextAlign.Center)
        }
    }
}