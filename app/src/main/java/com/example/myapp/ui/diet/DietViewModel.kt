package com.example.myapp.ui.diet

import androidx.lifecycle.ViewModel
import com.example.myapp.data.AppRepository

class DietViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    suspend fun updateDietOfCurrentUser(username: String, dietId: Int){
        appRepository.updateDietOfUser(username, dietId)
    }

    //insert 5 diets
}