package com.example.myapp.ui.components

import android.app.AlertDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue

@Composable
fun CategoryDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = page,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            val categories = listOf(
                "Основное блюдо",
                "Суп",
                "Салат",
                "Перекус"
            )
            var selectedCategory by remember {
                mutableStateOf(categories.first())
            }
            val onSelectionChange = { category: String ->
                selectedCategory = category
            }

            val openChooseFoodDialog = remember { mutableStateOf(false) }

            when {
                openChooseFoodDialog.value -> {
                    ChooseFoodDialog(
                        onDismissRequest = { openChooseFoodDialog.value = false },
                        onConfirmation = {
                            openChooseFoodDialog.value = false
                            println("Confirmation registered") // Add logic here to handle confirmation.
                        },
                        selectedCategory
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Column {
                    categories.forEach { category ->
                        val color = if (selectedCategory == category)
                            orange else Color.White

                        OutlinedButton(
                            onClick = {
                                onSelectionChange(category)
                            },
                            border = BorderStroke(1.dp, borderBlue),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = color,
                            ),
                            modifier = Modifier
                                .padding(0.dp) // margin
                                .fillMaxWidth(0.9f)
                                .height(80.dp)
                                .padding(10.dp) //padding
                        ) {
                            Text(
                                text = category,
                                color = textBlue,
                                fontSize = 16.sp
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(15.dp),
                    ) {
                        Text("Закрыть")
                    }
                    TextButton(
                        onClick = { openChooseFoodDialog.value = true },
                        modifier = Modifier.padding(15.dp),
                    ) {
                        Text("Далее")
                    }
                }
            }
        }
    }
}