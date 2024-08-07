package com.example.themeal_app.Data.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.adapter
import com.example.viewmodel.products.viewModel.ViewModelFactory
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
     lateinit var  btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn =findViewById(R.id.btn)
        btn.setOnClickListener{
            startActivity(Intent(this,MainActivity2::class.java))
        }

    }

}