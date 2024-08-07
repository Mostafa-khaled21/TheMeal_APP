package com.example.themeal_app.Data.UI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.adapter
import com.example.viewmodel.products.viewModel.ViewModelFactory
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment() {
    lateinit var recycel: RecyclerView
    private lateinit var adapter: adapter
    private lateinit var viewModel: MVVM
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel
    private lateinit var favoriteRecipesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycel = view.findViewById(R.id.recycleview)
        recycel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        favoriteRecipesTextView = view.findViewById(R.id.favorite_recipes_text_view)


        getViewModel()

        // Fetch categories
        viewModel.getAllCategories()
        viewModel.categoryResponse.observe(viewLifecycleOwner) { categoryResponse ->
            if (categoryResponse != null) {
                adapter = adapter(requireContext()) { favoriteRecipe ->
                    // Handle item click to save to favorites
                    favoriteRecipeViewModel.insert(favoriteRecipe)
                    Toast.makeText(requireContext(), "${favoriteRecipe.strMeal} added to favorites!", Toast.LENGTH_SHORT).show()
                }
                adapter.setCategoryList(categoryResponse)
                recycel.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        favoriteRecipeViewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner) { favoriteRecipes ->
            val favoriteRecipesText = favoriteRecipes.joinToString("\n") { it.strMeal }
            favoriteRecipesTextView.text = favoriteRecipesText
        }

        return view
    }

    private fun getViewModel() {

        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)


        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val favoriteRecipeRepository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val favoriteRecipeViewModelFactory = FavoriteRecipeViewModelFactory(favoriteRecipeRepository)
        favoriteRecipeViewModel = ViewModelProvider(this, favoriteRecipeViewModelFactory).get(
            FavoriteRecipeViewModel::class.java)
    }
}