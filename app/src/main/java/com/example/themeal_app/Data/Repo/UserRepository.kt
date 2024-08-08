package com.example.themeal_app.Data.Repo

import androidx.lifecycle.LiveData
import com.example.themeal_app.DatabaseModel.AllDatabase.Entity.User

interface UserRepository {
    fun registerUser(user: User)
    fun getUserByUsername(username: String): LiveData<User>
    fun getAllUsers(): LiveData<List<User>>
    fun getUserByCredentials(username: String, password: String): LiveData<User>
}