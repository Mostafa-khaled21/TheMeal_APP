package com.example.themeal_app.Data.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.splashscreen, SplashFragment())
            }
        }
    }
}
