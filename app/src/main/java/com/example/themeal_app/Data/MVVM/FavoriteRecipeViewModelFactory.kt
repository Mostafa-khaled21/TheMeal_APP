package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepository

class FavoriteRecipeViewModelFactory ( private val repository: FavoriteRecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteRecipeViewModel(repository) as T
    }
}