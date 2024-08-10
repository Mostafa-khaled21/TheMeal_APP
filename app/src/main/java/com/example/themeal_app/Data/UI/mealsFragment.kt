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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.adapter.mealsAdapter
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.searchAdapter
import com.example.viewmodel.products.viewModel.ViewModelFactory

class mealsFragment : Fragment() {
    lateinit var recycel: RecyclerView
    private lateinit var adapter: mealsAdapter
    private lateinit var favoriteRecipeViewModel: FavoriteRecipeViewModel
    private lateinit var viewModel: MVVM
    private val args: mealsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_meals, container, false)
        recycel=view.findViewById(R.id.recycleviewmeasl)
        recycel.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val category = args.category

        getViewModel()
        viewModel.getMealsByCategory(category)
        viewModel.categoryResponse.observe(viewLifecycleOwner) { categoryResponse ->
            Log.d("TAG", "onCreateView: $categoryResponse")

            if (categoryResponse.meals != null && categoryResponse.meals.isNotEmpty()) {
                adapter = mealsAdapter(requireContext())
                adapter.submitData(categoryResponse)
                recycel.adapter = adapter
            } else {
                Toast.makeText(requireContext(), "No meals available for this category", Toast.LENGTH_LONG).show()
                requireActivity().supportFragmentManager.popBackStack()


            }
        }
        return view
    }

    private fun getViewModel() {

        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)

    }
}