package com.example.viewmodel.network

import MealDetail
import com.example.themeal_app.model.allData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

interface RemoteDataSource {
    suspend fun getCategoryResponse(): Response<allData>
    suspend   fun getMealsByCategory(@Query("i") category:String):Response<allData>
    suspend fun   getMealByName(@Query("s") s:String):Response<allData>
    suspend  fun getRandomMeal():Response<allData>
    suspend fun getMealById(@Query("i") id:String):Response<allData>


}