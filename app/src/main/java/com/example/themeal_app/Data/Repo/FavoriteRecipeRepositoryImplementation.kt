package com.example.themeal_app.Data.Repo

import Meal
import androidx.lifecycle.LiveData
import com.example.themeal_app.DatabaseModel.AllDatabase.Dao.FavoriteRecipeDao


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