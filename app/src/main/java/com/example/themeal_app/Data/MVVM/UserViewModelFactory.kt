package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themeal_app.Data.Repo.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}
