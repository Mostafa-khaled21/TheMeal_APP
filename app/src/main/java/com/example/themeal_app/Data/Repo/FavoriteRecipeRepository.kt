package com.example.themeal_app.Data.Repo

import Meal
import androidx.lifecycle.LiveData

interface FavoriteRecipeRepository {

    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
    suspend fun insertFavoriteRecipe(recipe: Meal)
    suspend fun deleteFavoriteRecipe(recipe: Meal)
}