package com.example.themeal_app.Data.UI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.themeal_app.Data.MVVM.UserViewModel
import com.example.themeal_app.Data.MVVM.UserViewModelFactory
import com.example.themeal_app.Data.Repo.UserRepositoryImplementation
import com.example.themeal_app.DatabaseModel.AllDatabase.Database.AppDatabase
import com.example.themeal_app.R
import com.example.themeal_app.utils.SharedPreferencesManager

class LoginFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var ed_name :EditText
    lateinit var ed_pass :EditText
    lateinit var btn_login :Button
    lateinit var userViewModel: UserViewModel
    lateinit var txt_signup :TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view =inflater.inflate(R.layout.login_fragment, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        ed_name =view.findViewById(R.id.name_edit_text)
        ed_pass =view.findViewById(R.id.password_edit_text)
        btn_login =view.findViewById(R.id.sign_in_button)
        txt_signup =view.findViewById(R.id.moveRegister)

        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        val userRepository = UserRepositoryImplementation(userDao)
        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository)
        ).get(UserViewModel::class.java)

        btn_login.setOnClickListener{
            var name= ed_name.text.toString().trim()
            var pass= ed_pass.text.toString().trim()

            val editor = sharedPreferences.edit()
            if (name.isNotEmpty() && pass.isNotEmpty()) {
                // Observe login status
                userViewModel.loginUser(name, pass)
                userViewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(requireContext(), "Name or password is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter both name and password", Toast.LENGTH_LONG).show()
            }
        }
        txt_signup.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

        }


        return  view
    }


}
