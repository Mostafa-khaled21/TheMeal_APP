package com.example.themeal_app.DatabaseModel.AllDatabase.Dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themeal_app.DatabaseModel.AllDatabase.model.MealDB
import com.example.themeal_app.DatabaseModel.model.Meal

@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRecipe(recipe: Meal)

    @Delete
    fun deleteFavoriteRecipe(recipe: Meal)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
}