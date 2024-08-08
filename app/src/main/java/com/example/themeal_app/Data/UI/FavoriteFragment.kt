package com.example.themeal_app.Data.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.R
import com.example.themeal_app.Data.adapter.favAdapter

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: favAdapter
    private var favoriteRecipes = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recipeAdapter = favAdapter(favoriteRecipes) { recipe ->
            removeRecipe(recipe)
        }
        recyclerView.adapter = recipeAdapter

        loadFavorites()

        return view
    }

    private fun loadFavorites() {
        val sharedPreferences = context?.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favorites = sharedPreferences?.getStringSet("favorite_recipes", mutableSetOf()) ?: mutableSetOf()
        favoriteRecipes.clear()
        favoriteRecipes.addAll(favorites)
        recipeAdapter.notifyDataSetChanged()
    }

    private fun removeRecipe(recipe: String) {
        favoriteRecipes.remove(recipe)
        val sharedPreferences = context?.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val favorites = sharedPreferences?.getStringSet("favorite_recipes", mutableSetOf()) ?: mutableSetOf()
        favorites.remove(recipe)
        editor?.putStringSet("favorite_recipes", favorites)
        editor?.apply()
        recipeAdapter.notifyDataSetChanged()
    }
}
