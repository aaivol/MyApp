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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textBlue

@Composable
fun CategoryDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    mealType: String
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
            val types = listOf(
                "Основное блюдо",
                "Суп",
                "Салат",
                "Перекус"
            )
            var selectedType by remember {
                mutableStateOf(types.first())
            }
            val onSelectionChange = { type: String ->
                selectedType = type
            }

            val openChooseFoodDialog = remember { mutableStateOf(false) }

            when {
                openChooseFoodDialog.value -> {
                    ChooseFoodDialog(
                        onDismissRequest = { openChooseFoodDialog.value = false },
                        onConfirmation = {
                            openChooseFoodDialog.value = false
                        },
                        selectedType,
                        homeViewModel,
                        mealType
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
                    types.forEach { type ->
                        val color = if (selectedType == type)
                            orange else Color.White

                        OutlinedButton(
                            onClick = {
                                onSelectionChange(type)
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
                                text = type,
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