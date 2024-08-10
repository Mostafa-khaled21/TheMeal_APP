package com.example.themeal_app.Data.Repo

import com.example.themeal_app.DatabaseModel.model.allData
import com.example.viewmodel.network.ApiClient
import retrofit2.Response

class RandomImageRepositoryImplementation(private val apiClient: ApiClient) : RandomImageRepository {
    override suspend fun getRandomMeal(): Response<allData> {
        return apiClient.getRandomMeal()
    }
}