package com.example.themeal_app.Data.UI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.adapter
import com.example.viewmodel.products.viewModel.ViewModelFactory
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment() {
    lateinit var recycel : RecyclerView
    private lateinit var adapter : adapter
    lateinit var ViewModel: MVVM
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
         getViewModel()
        //ViewModel = ViewModelProvider(this, ViewModelFactory(ProductRepositoryImplementation(ApiClient))).get(MVVM::class.java)

        ViewModel.getAllCategories()
        ViewModel.categoryResponse.observe(viewLifecycleOwner,{ categoryResponse ->
            if (categoryResponse != null) {
                adapter = adapter(requireContext())
                adapter.setCategoryList(categoryResponse)
                recycel.adapter = adapter
                adapter.notifyDataSetChanged()

            }
        })

        return view
    }
    private fun getViewModel(){
        val ViewModelFactory = ViewModelFactory(
            ProductRepositoryImplementation(ApiClient)
        )
        ViewModel = ViewModelProvider(this,ViewModelFactory).get(MVVM::class.java)
    }


}