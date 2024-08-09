package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepository
import com.example.themeal_app.DatabaseModel.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteRecipeViewModel(private val repository: FavoriteRecipeRepository) : ViewModel() {
    fun insert(favoriteRecipe: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteRecipe(favoriteRecipe)
        }
    }

    fun delete(favoriteRecipe: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteRecipe(favoriteRecipe)
        }
    }

    fun getAllFavoriteRecipes(): LiveData<List<Meal>> {
        return repository.getAllFavoriteRecipes()
    }
}