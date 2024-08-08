package com.example.themeal_app.Data.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.postDelayed({

            if (SharedPreferencesManager.isLoggedIn(requireContext())) {

                parentFragmentManager.commit {
                    replace(R.id.splashscreen, HomeFragment())
                }
            } else {

                parentFragmentManager.commit {
                    replace(R.id.splashscreen, LoginFragment())
                }
            }
        }, 2000)
    }
}
