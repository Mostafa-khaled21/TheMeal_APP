package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themeal_app.Data.Repo.RandomImageRepository
import com.example.viewmodel.products.Repo.mealRepository

class RandomImageViewModelFactory(private val randomImageRepository: RandomImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomImageViewModel(randomImageRepository) as T

}
}