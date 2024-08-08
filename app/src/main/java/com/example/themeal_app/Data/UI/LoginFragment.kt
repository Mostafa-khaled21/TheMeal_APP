package com.example.themeal_app.Data.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton: Button = view.findViewById(R.id.sign_in_button)

        loginButton.setOnClickListener {

            SharedPreferencesManager.setLoggedIn(requireContext(), true)


            parentFragmentManager.commit {
                replace(R.id.splashscreen, HomeFragment())
            }
        }
    }
}
