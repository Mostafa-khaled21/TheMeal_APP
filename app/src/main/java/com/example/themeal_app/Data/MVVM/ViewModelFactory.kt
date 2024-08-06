package com.example.viewmodel.products.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.viewmodel.products.Repo.mealRepository

class ViewModelFactory(var mealRepository: mealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MVVM(mealRepository) as T
    }
}