package com.example.myapp.ui.filters

import android.text.Layout
import android.util.Log
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.DataStoring
import com.example.myapp.data.user_filter.enFilterToRu
import com.example.myapp.data.user_filter.nameToBit
import com.example.myapp.dataStore
import com.example.myapp.ui.AppViewModelProvider
import com.example.myapp.ui.home.HomeDestination
import com.example.myapp.ui.home.HomeViewModel
import com.example.myapp.ui.navigation.NavigationDestination
import com.example.myapp.ui.theme.borderBlue
import com.example.myapp.ui.theme.login
import com.example.myapp.ui.theme.orange
import com.example.myapp.ui.theme.page
import com.example.myapp.ui.theme.textAccent
import com.example.myapp.ui.theme.textBlue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object FiltersDestination : NavigationDestination {
    override val route = "filters"
    override val titleRes = R.string.app_name
}

//FILTERS SCREEN
@Composable
fun FiltersScreen(
    navigateToHome: () -> Unit,
    viewmodel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //get current username from datastore
    val context = LocalContext.current
    val usernameStored = context.dataStore.data.collectAsState(
        initial = DataStoring()
    ).value.user.name

    LaunchedEffect(key1 = usernameStored){
        //update username in viewmodel
        viewmodel.updateName(usernameStored)
        Log.d("tag1", "username stored: $usernameStored")
        viewmodel.getUser()
        viewmodel.checkCurrentFilters()
    }

    val coroutineScope = rememberCoroutineScope()

    //filters
    val userFilters = viewmodel.currentFilters
    var selectedBits = remember { mutableStateListOf<Int>() }

    FiltersBody(
        selectedBits,
        userFilters,
        toHome = {
            coroutineScope.launch {
                viewmodel.updateFilters(selectedBits)
            }
            navigateToHome()
        }
    )
}

@Composable
fun FiltersBody(
    selectedBits: MutableList<Int>,
    userFilters: List<String>,
    toHome: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(page)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FiltersText()
        DietGoalButtons(
            selectedBits,
            userFilters
        )
        OutlinedButton(
            onClick = toHome,
            //connect to viewmodel with filters
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
fun DietGoalButtons(
    selectedBits: MutableList<Int>,
    userFilters: List<String>
) {
    val filters = listOf(
        "Диабет",
        "Аллергия",
        "Ожирение",
        "Гастрит",
        "Без мяса",
        "Веган",
        "Без молока",
        "Жду ребенка",
        "Кормление",
    )

    var selectedFilters = remember {
        mutableStateListOf<String>()
    }

    val onSelectionChange = { filter: String ->
        if (!selectedFilters.contains(filter)){
            selectedFilters.add(filter)
            selectedBits.add(filters.indexOf(filter))
        }
    }


    LaunchedEffect(key1 = userFilters){
        Log.d("tag1", "on screen 2: $userFilters")
        if (userFilters.isNotEmpty()){
            selectedFilters.clear()
            userFilters.forEach {
                selectedFilters.add(enFilterToRu[it]!!)
                val index = nameToBit[it]
                if (index != null){
                    selectedBits.add(index)
                }
            }
            Log.d("tag1", selectedFilters.joinToString(" "))

        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    ) {
        filters.forEach { filter ->
            val isSelected = selectedFilters.contains(filter)
            val color = if (isSelected) orange else login
            val len = filter.length
            val btnwid = (len * 22).dp

            OutlinedButton(
                onClick = {
                    if (isSelected) {
                        selectedFilters.remove(filter)
                        selectedBits.remove(filters.indexOf(filter))
                        Log.d("tag1", selectedBits.joinToString(" "))
                        Log.d("tag1", filter)
                        Log.d("tag1", filters.indexOf(filter).toString())
                    }
                    else {
                        onSelectionChange(filter)
                    }
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