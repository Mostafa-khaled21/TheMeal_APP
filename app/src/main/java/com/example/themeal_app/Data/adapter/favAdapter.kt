package com.example.themeal_app.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themeal_app.Data.UI.SearchFragmentDirections
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R
import com.example.themeal_app.UI.Fragments.FavoriteFragment
import com.example.themeal_app.UI.Fragments.FavoriteFragmentDirections

class favAdapter(
     val navController: NavController,
    private val onDeleteClick: (Meal) -> Unit
) : RecyclerView.Adapter<favAdapter.FavoriteRecipeViewHolder>() {

    private var favoriteRecipes: List<Meal> = listOf()

    class FavoriteRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeDescriptionTextView: TextView = view.findViewById(R.id.recipe_description)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val imagemod: ImageView = view.findViewById(R.id.fav_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = favoriteRecipes[position]
        holder.recipeDescriptionTextView.text = recipe.strMeal

        // Load image using Glide or another image loading library
        Glide.with(holder.itemView.context)
            .load(recipe.strMealThumb)
            .into(holder.imagemod)
        holder.itemView.setOnClickListener {
            val position = position
            val name = recipe.idMeal
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToRecipeDetailFragment(name,position.toString())
            navController.navigate(action)

        }

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



