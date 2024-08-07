package com.example.viewmodel.products.Repo

import com.example.themeal_app.DatabaseModel.model.allData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

interface mealRepository {
    suspend fun getCategoryResponse(): Response<allData>
    suspend   fun getMealsByCategory(@Query("i") category:String):Response<allData>
    suspend fun   getMealByName(@Query("s") s:String):Response<allData>
    suspend  fun getRandomMeal():Response<allData>
    suspend fun getMealById(@Query("i") id:String):Response<allData>
}