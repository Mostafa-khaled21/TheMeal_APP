package com.example.themeal_app.Data.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.AppDatabase
import com.example.themeal_app.DatabaseModel.AllDatabase.Entity.User
import com.example.themeal_app.Data.MVVM.MVVM
import com.example.themeal_app.Data.MVVM.UserViewModel
import com.example.themeal_app.Data.MVVM.UserViewModelFactory
import com.example.themeal_app.Data.Repo.UserRepositoryImplementation
import com.example.themeal_app.R
import com.example.viewmodel.network.ApiClient
import com.example.viewmodel.products.Repo.ProductRepositoryImplementation
import com.example.viewmodel.products.adapter.adapter
import com.example.viewmodel.products.viewModel.ViewModelFactory
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        lateinit var userViewModel: UserViewModel
        val userDao = AppDatabase.getDatabase(this).userDao()
        val userRepository = UserRepositoryImplementation(userDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        ).get(UserViewModel::class.java)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val getUserButton = findViewById<Button>(R.id.getUserButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val user = User(username, password)
                userViewModel.registerUser(user)
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        getUserButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            if (username.isNotEmpty()) {
                userViewModel.getUserByUsername(username)
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe userLiveData to update the TextView
        userViewModel.userLiveData.observe(this, Observer { user ->
            if (user != null) {
                resultTextView.text = "Username: ${user.username}"
            } else {
                resultTextView.text = "User not found"
            }
        })
    }
}