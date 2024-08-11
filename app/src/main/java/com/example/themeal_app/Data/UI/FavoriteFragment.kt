package com.example.themeal_app.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.R
import com.example.themeal_app.UI.Adapters.favAdapter
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteRecipeAdapter: favAdapter
    private lateinit var viewModel: FavoriteRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize the adapter with an empty list and a delete action
        favoriteRecipeAdapter = favAdapter { meal ->
            // Handle delete button click
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    viewModel.delete(meal)
                    Toast.makeText(requireContext(), "${meal.strMeal} removed from favorites", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Failed to remove from favorites: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = favoriteRecipeAdapter

        // Initialize the ViewModel
        getFavoriteViewModel()

        // Observe the LiveData from ViewModel
        viewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner) { favoriteMeals ->
            // Update the adapter with the list of favorite recipes
            favoriteRecipeAdapter.updateRecipes(favoriteMeals)
        }

        return view
    }

    private fun getFavoriteViewModel() {
        val favoriteViewModelFactory = FavoriteRecipeViewModelFactory(
            FavoriteRecipeRepositoryImplementation(
                FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
            )
        )
        viewModel = ViewModelProvider(this, favoriteViewModelFactory).get(FavoriteRecipeViewModel::class.java)
    }
}


