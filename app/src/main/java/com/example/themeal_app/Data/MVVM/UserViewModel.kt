package com.example.themeal_app.Data.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themeal_app.DatabaseModel.AllDatabase.Entity.User
import com.example.themeal_app.Data.Repo.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun registerUser(user: User) {
        userRepository.registerUser(user)
    }

    fun getUserByUsername(username: String) {
        userRepository.getUserByUsername(username).observeForever { user ->
            _userLiveData.value = user
        }
    }
}