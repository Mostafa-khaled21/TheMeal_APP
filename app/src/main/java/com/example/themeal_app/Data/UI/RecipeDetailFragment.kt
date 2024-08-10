package com.example.themeal_app.UI.Fragments

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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.Data.UI.mealsFragmentArgs
import com.example.themeal_app.Data.adapter.mealsAdapter
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.viewModel.ViewModelFactory

class RecipeDetailFragment : Fragment() {

    private lateinit var recipeImageView: ImageView
    private lateinit var recipeDescriptionTextView: TextView
    private lateinit var toggleDescriptionTextView: TextView
    private lateinit var favoriteButton: Button
    private val args:   RecipeDetailFragmentArgs by navArgs()
    private lateinit var viewModel: MVVM

    private var isExpanded = false
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel
    private lateinit var meal: Meal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        recipeImageView = view.findViewById(R.id.img)
        recipeDescriptionTextView = view.findViewById(R.id.descr)
        toggleDescriptionTextView = view.findViewById(R.id.toggleTextView)
        favoriteButton = view.findViewById(R.id.favoriteButton)
        val position = args.position
        getViewModel()
        viewModel.getAllCategories()
        viewModel.categoryResponse.observe(viewLifecycleOwner) { meals ->
            Log.d("TAG11111", "onCreateView: meals = $meals")
            Log.d("TAG11111", "onCreateView: mealsDetail = ${meals?.mealsDetail}")

            if (meals?.mealsDetail != null && position != null) {
                val safePosition = position.toIntOrNull()
                Log.d("TAG11111", "onCreateView: safePosition = $safePosition")

                if (safePosition != null && safePosition in meals.mealsDetail.indices) {
                    val mealDetail = meals.mealsDetail[safePosition]
                    recipeDescriptionTextView.text = mealDetail.strMeal
                    Glide.with(this).load(mealDetail.strMealThumb).into(recipeImageView)
                    meal = Meal(mealDetail.idMeal, "", mealDetail.strMealThumb)
                } else {
                    Log.e("RecipeDetailFragment", "Invalid position or position out of bounds")
                    Toast.makeText(requireContext(), "Invalid position or position out of bounds", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.e("RecipeDetailFragment", "Meals data or mealsDetail is null or position is null")
                Toast.makeText(requireContext(), "No meal details available", Toast.LENGTH_SHORT).show()
            }
        }

        //  ViewModel
        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val repository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val viewModelFactory = FavoriteRecipeViewModelFactory(repository)
        favoriteRecipeViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteRecipeViewModel::class.java)

        //  Bundle
     //   val recipeId = arguments?.getString("recipe_id") ?: ""
     //   val recipeDescription = arguments?.getString("recipe_description") ?: "Recipe description goes here..."
     //   val recipeImageUrl = arguments?.getString("recipe_image_url") ?: ""


      //  recipeDescriptionTextView.text = recipeDescription
     //   Glide.with(this).load(recipeImageUrl).into(recipeImageView)


       // meal = Meal(recipeId, "", recipeImageUrl)

        // toggle
        toggleDescriptionTextView.setOnClickListener {
            isExpanded = !isExpanded
            recipeDescriptionTextView.maxLines = if (isExpanded) Integer.MAX_VALUE else 2
            toggleDescriptionTextView.text = if (isExpanded) "Show Less" else "Show More"
        }


        favoriteButton.setOnClickListener {
            saveRecipeToFavorites()

        }

        return view
    }

    private fun saveRecipeToFavorites() {

        favoriteRecipeViewModel.insert(meal)

        Toast.makeText(requireContext(), " Racipe added to favorites!", Toast.LENGTH_SHORT).show()
    }
    private fun getViewModel() {

        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)

    }
}
