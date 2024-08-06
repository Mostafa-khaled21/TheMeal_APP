package com.example.viewmodel.network

import FoodApi
import MealDetail
import com.example.easyfood.data.retrofit.RetrofitClient
import com.example.themeal_app.model.allData
import retrofit2.Call
import retrofit2.Response

object ApiClient :RemoteDataSource{
    override suspend fun getCategoryResponse(): Response<allData> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getCategories()
    }

    override suspend fun getMealsByCategory(category: String): Response<allData> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealsByCategory(category)
    }

    override suspend fun getMealByName(s: String): Response<allData> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealByName(s)
    }

    override suspend fun getRandomMeal(): Response<allData> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getRandomMeal()
    }

    override suspend fun getMealById(id: String): Response<allData> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealById(id)
    }

}