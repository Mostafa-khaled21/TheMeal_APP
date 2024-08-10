package com.example.themeal_app.Data.UI


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.Data.adapter.RandomImageAdapter
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.adapter
import com.example.viewmodel.products.viewModel.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var randomImageRecyclerView: RecyclerView
    private lateinit var adapter: adapter
    private lateinit var randomImageAdapter: RandomImageAdapter
    private lateinit var viewModel: MVVM
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize recycleviews
        recyclerView = view.findViewById(R.id.recycl)
        randomImageRecyclerView = view.findViewById(R.id.imagerecycleview)
        navController=findNavController()


        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL,false)
        //favourite Handling
        adapter = adapter(requireContext(),navController) { favoriteRecipe, isAdding ->
            if (isAdding) {
                favoriteRecipeViewModel.insert(favoriteRecipe)
                Toast.makeText(requireContext(), "${favoriteRecipe.strMeal} added to favorites!", Toast.LENGTH_SHORT).show()
            } else {
                favoriteRecipeViewModel.delete(favoriteRecipe)
                Toast.makeText(requireContext(), "${favoriteRecipe.strMeal} removed from favorites!", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter

        // RecyclerView for random images
        randomImageRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        randomImageAdapter = RandomImageAdapter(requireContext())
        randomImageRecyclerView.adapter = randomImageAdapter


        getViewModels()


        viewModel.getAllCategories()
        viewModel.categoryResponse.observe(viewLifecycleOwner) { categoryResponse ->
            if (categoryResponse != null) {
                adapter.setCategoryList(categoryResponse)
            }
        }


        favoriteRecipeViewModel.getAllFavoriteRecipes().observe(viewLifecycleOwner) { favoriteRecipes ->
            val favoriteMealIds = favoriteRecipes.map { it.idMeal }.toSet()
            adapter.setFavoriteMeals(favoriteMealIds)
        }

        fetchRandomImages()

        return view
    }

    private fun fetchRandomImages() {
        viewModel.getRandomMeal()
        viewModel.categoryResponse.observe(viewLifecycleOwner) { response ->
            val meals = response?.meals
            if (!meals.isNullOrEmpty()) {
                randomImageAdapter.setImageList(meals)
            } else {
                Log.e("HomeFragment", "No random meals found")
            }
        }
    }

    private fun getViewModels() {
        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)

        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val favoriteRecipeRepository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val favoriteRecipeViewModelFactory = FavoriteRecipeViewModelFactory(favoriteRecipeRepository)
        favoriteRecipeViewModel = ViewModelProvider(this, favoriteRecipeViewModelFactory).get(
            FavoriteRecipeViewModel::class.java
        )
    }
}
