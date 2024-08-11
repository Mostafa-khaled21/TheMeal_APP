package com.example.themeal_app.Data.Repo

import RandomMealResponse
import com.example.viewmodel.network.ApiClient
import retrofit2.Response

class RandomImageRepositoryImplementation(private val apiClient: ApiClient) : RandomImageRepository {
    override suspend fun getRandomMeal(): Response<RandomMealResponse> {
        return apiClient.getRandomMeal()    }
}