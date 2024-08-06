package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themeal_app.model.allData
import com.example.viewmodel.products.Repo.mealRepository
import kotlinx.coroutines.launch

class MVVM(private val mealRepository: mealRepository) : ViewModel() {
    private val _categoryResponse = MutableLiveData<allData>()
    val categoryResponse: LiveData<allData> = _categoryResponse

    fun getAllCategories() {
        viewModelScope.launch {
            val result = mealRepository.getCategoryResponse()
            if (result.isSuccessful) {
                _categoryResponse.value = result.body()
            }
        }
    }

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            val result = mealRepository.getMealsByCategory(category)
            if (result.isSuccessful) {
                _categoryResponse.value = result.body()
            }
        }
    }

    fun getMealByName(name: String) {
        viewModelScope.launch {
            val result = mealRepository.getMealByName(name)
            if (result.isSuccessful) {
                _categoryResponse.value = result.body()
            }
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            val result = mealRepository.getRandomMeal()
            if (result.isSuccessful) {
                _categoryResponse.value = result.body()
            }
        }
    }

    fun getMealById(id: String) {
        viewModelScope.launch {
            val result = mealRepository.getMealById(id)
            if (result.isSuccessful) {
                _categoryResponse.value = result.body()
            }
        }
    }
}
