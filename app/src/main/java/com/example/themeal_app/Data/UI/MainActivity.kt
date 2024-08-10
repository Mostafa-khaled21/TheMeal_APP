package com.example.themeal_app.Data.UI

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController



        // Check if the user is logged in, then navigate to MainActivity2
//        if (isUserLoggedIn()) {

//        } else {
//            navController.navigate(R.id.splashFragment)
//        }
    }

    private fun isUserLoggedIn(): Boolean {
        // Implement your logic to check if the user is logged in
        return false // Replace with actual login check
    }
}
