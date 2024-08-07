package com.example.themeal_app.Data.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.themeal_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener  {
    private lateinit var  navView: NavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var bottomNavView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        bottomNavView = findViewById(R.id.bottom_navigation)
        bottomNavView.setOnItemSelectedListener(this)


        drawer = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawer, R.string.nav_drawer_start,
            R.string.nav_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {

            }
            R.id.action_fav -> {
            }
            R.id.action_category -> {
            }
        }
        drawer.closeDrawers()
        return false
    }
}