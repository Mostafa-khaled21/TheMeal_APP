package com.example.themeal_app.DatabaseModel.AllDatabase.Dao

import Meal
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: Meal)

    @Delete
    suspend fun deleteFavoriteRecipe(recipe: Meal)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
}
