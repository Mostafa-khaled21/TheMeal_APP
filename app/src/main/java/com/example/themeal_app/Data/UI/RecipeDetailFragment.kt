package com.example.themeal_app.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModelFactory
import com.example.themeal_app.Data.Repo.FavoriteRecipeRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.FavoriteDatabase
import com.example.themeal_app.DatabaseModel.model.Meal
import com.example.themeal_app.R

class RecipeDetailFragment : Fragment() {

    private lateinit var recipeImageView: ImageView
    private lateinit var recipeDescriptionTextView: TextView
    private lateinit var toggleDescriptionTextView: TextView
    private lateinit var favoriteButton: Button

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

        //  ViewModel
        val favoriteRecipeDao = FavoriteDatabase.getDatabase(requireContext()).favoriteRecipeDao()
        val repository = FavoriteRecipeRepositoryImplementation(favoriteRecipeDao)
        val viewModelFactory = FavoriteRecipeViewModelFactory(repository)
        favoriteRecipeViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteRecipeViewModel::class.java)

        //  Bundle
        val recipeId = arguments?.getString("recipe_id") ?: ""
        val recipeDescription = arguments?.getString("recipe_description") ?: "Recipe description goes here..."
        val recipeImageUrl = arguments?.getString("recipe_image_url") ?: ""


        recipeDescriptionTextView.text = recipeDescription
        Glide.with(this).load(recipeImageUrl).into(recipeImageView)


        meal = Meal(recipeId, "", recipeImageUrl)

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

}
