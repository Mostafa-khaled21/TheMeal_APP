package com.example.themeal_app.Data.UI


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.MVVM.RandomImageViewModel
import com.example.themeal_app.Data.MVVM.RandomImageViewModelFactory
import com.example.themeal_app.Data.Repo.RandomImageRepositoryImplementation
import com.example.themeal_app.Data.adapter.RandomImageAdapter
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
    private lateinit var randomImageViewModel: RandomImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycl)
        randomImageRecyclerView = view.findViewById(R.id.imagerecycleview)

        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        adapter = adapter(requireContext())
        recyclerView.adapter = adapter

        randomImageRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        randomImageAdapter = RandomImageAdapter(requireContext())
        randomImageRecyclerView.adapter = randomImageAdapter

        // Initialize ViewModels
        getViewModels()
        observeViewModels()

        return view
    }

    private fun getViewModels() {
        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)


        val randomImageRepository = RandomImageRepositoryImplementation(ApiClient)
        val randomImageViewModelFactory = RandomImageViewModelFactory(randomImageRepository)
        randomImageViewModel = ViewModelProvider(this, randomImageViewModelFactory).get(RandomImageViewModel::class.java)
    }

    private fun observeViewModels() {

        viewModel.categoryResponse.observe(viewLifecycleOwner) { categoryResponse ->
            if (categoryResponse != null) {
                adapter.setCategoryList(categoryResponse)
            } else {
                Log.e("HomeFragment", "No category data available")
            }
        }

        randomImageViewModel.randomImageResponse.observe(viewLifecycleOwner) { response ->
            val meals = response?.meals
            if (!meals.isNullOrEmpty()) {
                randomImageAdapter.setImageList(meals)
            } else {
                Log.e("HomeFragment", "No random meals found")
            }
        }

        // Fetch data from ViewModels
        viewModel.getAllCategories()
        randomImageViewModel.fetchRandomImages()
    }
}
