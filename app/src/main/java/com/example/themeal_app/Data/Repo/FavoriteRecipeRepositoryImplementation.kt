package com.example.themeal_app.Data.Repo

import androidx.lifecycle.LiveData
import com.example.themeal_app.DatabaseModel.AllDatabase.Dao.FavoriteRecipeDao
import com.example.themeal_app.DatabaseModel.AllDatabase.model.MealDB
import com.example.themeal_app.DatabaseModel.model.Meal

class FavoriteRecipeRepositoryImplementation(private val favoriteRecipeDao: FavoriteRecipeDao) : FavoriteRecipeRepository {
    override suspend fun insertFavoriteRecipe(recipe: Meal) {
        favoriteRecipeDao.insertFavoriteRecipe(recipe)
    }

    override suspend fun deleteFavoriteRecipe(recipe: Meal) {
        favoriteRecipeDao.deleteFavoriteRecipe(recipe)
    }

    override fun getAllFavoriteRecipes(): LiveData<List<Meal>> {
        return favoriteRecipeDao.getAllFavoriteRecipes()
    }
}