package com.example.themeal_app.Data.UI

import MVVM
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.FavoriteRecipeViewModel
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.searchAdapter
import com.example.viewmodel.products.viewModel.ViewModelFactory


class SearchFragment : Fragment() {
   lateinit var btn_search :Button
   lateinit var ed_search :EditText
    lateinit var recycel: RecyclerView
    private lateinit var adapter: searchAdapter
    private lateinit var viewModel: MVVM
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val  view =inflater.inflate(R.layout.fragment_search, container, false)
        btn_search = view.findViewById(R.id.ic_search)
        ed_search =view.findViewById(R.id.ed_search)
        recycel =view.findViewById(R.id.recycleviewSearch)
        recycel.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        getViewModel()
        btn_search.setOnClickListener {
            val query = ed_search.text.toString()

            if (query.isNotEmpty()) {
                viewModel.getMealByNameForSearch(query)
                viewModel.mealByNameForSearch.observe(viewLifecycleOwner) { searchResponse ->

                    if (searchResponse != null) {
                        adapter = searchAdapter(requireContext())
                        adapter.submitData(searchResponse)
                        Log.d("TAG1", "onCreateView:${searchResponse} ")
                        recycel.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a meal name", Toast.LENGTH_LONG).show()
            }


        }

        return view
    }

    private fun getViewModel() {

        val apiViewModelFactory = ViewModelFactory(ProductRepositoryImplementation(ApiClient))
        viewModel = ViewModelProvider(this, apiViewModelFactory).get(MVVM::class.java)

    }

}