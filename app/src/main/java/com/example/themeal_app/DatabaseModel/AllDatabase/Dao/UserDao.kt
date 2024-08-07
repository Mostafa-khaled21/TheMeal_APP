package com.example.themeal_app.DatabaseModel.AllDatabase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.themeal_app.DatabaseModel.AllDatabase.Entity.User

@Dao
interface UserDao {
    @Insert
    fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): LiveData<User>

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
}