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
    private var recipes: List<Meal>,
    private val onDeleteClick: (Meal) -> Unit
) : RecyclerView.Adapter<favAdapter.RecipeViewHolder>() {


    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeTitle: TextView = view.findViewById(R.id.recipe_title)
        val recipeDescription: TextView = view.findViewById(R.id.recipe_description)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.strMeal
        holder.recipeDescription.text = recipe.strMealThumb


        holder.deleteButton.setOnClickListener {
            onDeleteClick(recipe)
        }
    }


    override fun getItemCount(): Int {
        return recipes.size
    }


    fun updateRecipes(newRecipes: List<Meal>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
