package com.example.themeal_app.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.R
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.UI.Adapters.favAdapter

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteRecipeAdapter: favAdapter
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        //  ViewModel
        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val repository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val viewModelFactory = FavoriteRecipeViewModelFactory(repository)
        favoriteRecipeViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteRecipeViewModel::class.java)

        //  Adapter
        favoriteRecipeAdapter = favAdapter { meal ->
            deleteRecipeFromFavorites(meal)
        }
        recyclerView.adapter = favoriteRecipeAdapter


        favoriteRecipeViewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner, Observer { meals ->
            favoriteRecipeAdapter.updateRecipes(meals)
        })

        return view
    }

    private fun deleteRecipeFromFavorites(meal: Meal) {
        favoriteRecipeViewModel.delete(meal)
        Toast.makeText(requireContext(), " Racipe remove from favorites!", Toast.LENGTH_SHORT).show()
    }
}
