package com.example.themeal_app.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R
import com.example.themeal_app.UI.Adapters.favAdapter

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: favAdapter
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)


        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val repository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val viewModelFactory = FavoriteRecipeViewModelFactory(repository)
        favoriteRecipeViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteRecipeViewModel::class.java)


        recipeAdapter = favAdapter(listOf()) { recipe ->
            deleteRecipe(recipe)
        }
        recyclerView.adapter = recipeAdapter


        favoriteRecipeViewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner, { recipes ->
            recipeAdapter.updateRecipes(recipes)
        })

        return view
    }

    private fun deleteRecipe(recipe: Meal) {
        favoriteRecipeViewModel.delete(recipe)
    }
}
