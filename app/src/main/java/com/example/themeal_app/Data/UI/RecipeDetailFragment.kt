package com.example.themeal_app.Data.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.themeal_app.R

class RecipeDetailFragment : Fragment() {
    private var isTextExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        val descriptionTextView = view.findViewById<TextView>(R.id.descr)
        val toggleTextView = view.findViewById<TextView>(R.id.toggleTextView)
        val favoriteButton = view.findViewById<Button>(R.id.favoriteButton)

        toggleTextView.setOnClickListener {
            if (isTextExpanded) {
                descriptionTextView.maxLines = 1
                toggleTextView.text = "Show More"
            } else {
                descriptionTextView.maxLines = Integer.MAX_VALUE
                toggleTextView.text = "Show Less"
            }
            isTextExpanded = !isTextExpanded
        }

        favoriteButton.setOnClickListener {
            addRecipeToFavorites("Steak")
        }

        return view
    }

    private fun addRecipeToFavorites(recipe: String) {
        val sharedPreferences = activity?.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val favorites = sharedPreferences?.getStringSet("favorite_recipes", mutableSetOf()) ?: mutableSetOf()
        favorites.add(recipe)
        editor?.putStringSet("favorite_recipes", favorites)
        editor?.apply()
    }
}
