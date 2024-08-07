package com.example.themeal_app.Data.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.themeal_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var navView: NavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navController: NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavView = findViewById(R.id.bottom_navigation)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragmentbottom -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.favoriteFragmentbottom -> {
                    navController.navigate(R.id.favoriteFragment)
                    true
                }

                R.id.searchFragmentbottom -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                else -> false
            }
        }


        drawer = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawer, R.string.nav_drawer_start,
            R.string.nav_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                navController.navigate(R.id.homeFragment)
                bottomNavView.selectedItemId = R.id.homeFragmentbottom
                drawer.closeDrawers()
                return true
            }

            R.id.action_fav -> {
                navController.navigate(R.id.favoriteFragment)
                bottomNavView.selectedItemId = R.id.favoriteFragmentbottom
                drawer.closeDrawers()
                return true
            }

            R.id.action_search -> {
                navController.navigate(R.id.searchFragment)
                bottomNavView.selectedItemId = R.id.searchFragmentbottom
                drawer.closeDrawers()
                return true
            }
        }
        drawer.closeDrawers()
        return false
    }
}