package com.example.themeal_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R

class favAdapter(
    private val onDeleteClick: (Meal) -> Unit
) : RecyclerView.Adapter<favAdapter.FavoriteRecipeViewHolder>() {

    private var favoriteRecipes: List<Meal> = listOf()

    class FavoriteRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeDescriptionTextView: TextView = view.findViewById(R.id.recipe_description)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = favoriteRecipes[position]
        holder.recipeDescriptionTextView.text = recipe.strMealThumb


           //delete
        holder.deleteButton.setOnClickListener {
            onDeleteClick(recipe)

        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    fun updateRecipes(newRecipes: List<Meal>) {
        favoriteRecipes = newRecipes
        notifyDataSetChanged()
    }
}
