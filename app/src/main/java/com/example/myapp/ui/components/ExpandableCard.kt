package com.example.myapp.ui.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapp.R
import com.example.myapp.ui.food.FoodScreen
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.textBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    title: String,
    titleFontWeight: FontWeight = FontWeight.Bold,
    padding: Dp = 12.dp,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(vertical = 10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        val openAlertDialog = remember { mutableStateOf(false) }

        // ...
        when {
            // ...
            openAlertDialog.value -> {
                DialogWithImage(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        openAlertDialog.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                        },
                    painter = painterResource(id = R.drawable.carbonara),
                    imageDescription = "Карбонара"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(10.dp),
                    text = title,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Row {
                    OutlinedButton(
                        onClick = { openAlertDialog.value = true },
                        border = BorderStroke(1.dp, borderBlue),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .padding(0.dp) // margin
                            .width(120.dp)
                            .height(140.dp)
                            .padding(10.dp) //padding
                    ) {
                        Text(
                            text = "+",
                            color = textBlue,
                            fontSize = 26.sp
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "My Title"
    )
}