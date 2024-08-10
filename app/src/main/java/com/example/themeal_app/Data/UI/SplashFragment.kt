package com.example.themeal_app.Data.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (SharedPreferencesManager.isLoggedIn(requireContext())) {
                val intent = Intent(requireContext(), MainActivity2::class.java)
                startActivity(intent)
                requireActivity().finish()            } else {
                findNavController().navigate(R.id.action_splashFragment2_to_loginFragment3)
            }
        }, 2000)


    }



}

