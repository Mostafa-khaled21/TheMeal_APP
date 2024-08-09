package com.example.themeal_app.Data.Repo

import androidx.lifecycle.LiveData
import com.example.themeal_app.DatabaseModel.model.Meal

interface FavoriteRecipeRepository {

    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
    suspend fun insertFavoriteRecipe(recipe: Meal)
    suspend fun deleteFavoriteRecipe(recipe: Meal)
}