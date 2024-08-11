package com.example.themeal_app.UI.Fragments

import MVVM
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.Data.UI.MainActivity2
import com.example.themeal_app.Data.UI.mealsFragmentArgs
import com.example.themeal_app.Data.adapter.mealsAdapter
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.DatabaseModel.AllDatabase.model.MealDB
import com.example.themeal_app.DatabaseModel.model.Meal

import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.viewModel.ViewModelFactory
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {

    private lateinit var recipeImageView: ImageView
    private lateinit var img_youtube: ImageView
    private lateinit var recipeDescriptionTextView: TextView
    private lateinit var toggleDescriptionTextView: TextView
    private lateinit var recipetitleTextView: TextView
    private lateinit var favoriteButton: Button

    private val args: RecipeDetailFragmentArgs by navArgs()
    private lateinit var viewModel: MVVM
    private lateinit var favoriteViewModel: FavoriteRecipeViewModel
    private lateinit var meal: Meal

    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        // Initialize Views
        recipeImageView = view.findViewById(R.id.img)
        img_youtube = view.findViewById(R.id.img_youtube)
        recipeDescriptionTextView = view.findViewById(R.id.descr)
        toggleDescriptionTextView = view.findViewById(R.id.toggleTextView)
        recipetitleTextView = view.findViewById(R.id.title_det)
        favoriteButton = view.findViewById(R.id.favoriteButton)

        // Initialize ViewModels
        getViewModel()
        getFavoriteViewModel()

        // Fetch meal data
        val mealName = args.name.toString()
        viewModel.getMealById(mealName)

        viewModel.mealById.observe(viewLifecycleOwner) { mealResponse ->
            mealResponse?.let {
                val mealDetail = it.meals.get(0)
                recipetitleTextView.text = mealDetail.strMeal
                recipeDescriptionTextView.text = mealDetail.strInstructions

                Glide.with(this).load(mealDetail.strMealThumb).into(recipeImageView)

                val link = mealDetail.strYoutube
                img_youtube.setOnClickListener {
                    if (link != null) {
                        val uri: Uri = Uri.parse(link)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "No YouTube link available", Toast.LENGTH_SHORT).show()
                    }
                }

                meal = Meal(
                    idMeal = mealDetail.idMeal,
                    strMeal = mealDetail.strMeal,
                    strMealThumb = mealDetail.strMealThumb
                )
            } ?: run {
                Toast.makeText(requireContext(), "Failed to load meal details", Toast.LENGTH_SHORT).show()
            }
        }

        // Toggle description text
        toggleDescriptionTextView.setOnClickListener {
            isExpanded = !isExpanded
            recipeDescriptionTextView.maxLines = if (isExpanded) Int.MAX_VALUE else 2
            toggleDescriptionTextView.text = if (isExpanded) "Show Less" else "Show More"
        }

        // Handle favorite button click
        favoriteButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    favoriteViewModel.insert(meal)
                    Toast.makeText(requireContext(), "Added to Favorites", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Failed to add to Favorites: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    private fun getViewModel() {
        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)
    }

    private fun getFavoriteViewModel() {
        val favoriteViewModelFactory = FavoriteRecipeViewModelFactory(FavoriteRecipeRepositoryImplementation(FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()))
        favoriteViewModel = ViewModelProvider(this, favoriteViewModelFactory).get(FavoriteRecipeViewModel::class.java)
    }
}



