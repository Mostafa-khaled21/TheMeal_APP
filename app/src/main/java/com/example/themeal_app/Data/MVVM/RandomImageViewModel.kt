package com.example.themeal_app.Data.MVVM

import RandomMealResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themeal_app.Data.Repo.RandomImageRepository
import com.example.viewmodel.products.Repo.mealRepository
import kotlinx.coroutines.launch

class RandomImageViewModel(private val randomImageRepository: RandomImageRepository) : ViewModel() {
    private val _randomImageResponse = MutableLiveData<RandomMealResponse>()
    val randomImageResponse: LiveData<RandomMealResponse> = _randomImageResponse
    fun fetchRandomImages() {
        viewModelScope.launch {
            val result = randomImageRepository.getRandomMeal()
            if (result.isSuccessful) {
                _randomImageResponse.value = result.body()
            } else {
                Log.e("RandomImageViewModel", "Error fetching random images: ${result.message()}")
            }
        }
    }
}
