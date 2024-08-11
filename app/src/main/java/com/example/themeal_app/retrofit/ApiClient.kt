package com.example.viewmodel.network

import CategoryResponse
import FoodApi
import MealDetail
import MealsResponse
import RandomMealResponse

import com.example.easyfood.data.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

object ApiClient :RemoteDataSource{
    override suspend fun getCategoryResponse(): Response<CategoryResponse> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getCategories()
    }

    override suspend fun getMealsByCategory(category: String): Response<MealsResponse> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealsByCategory(category)
    }

    override suspend fun getMealByName(s: String): Response<MealDetail> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealByName(s)
    }

    override suspend fun getRandomMeal(): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getRandomMeal()
    }

    override suspend fun getMealById(id: String): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealById(id)
    }

    override suspend fun getMealByNameForSearch(name: String): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(FoodApi::class.java).getMealByNameForSearch(name)
    }

}