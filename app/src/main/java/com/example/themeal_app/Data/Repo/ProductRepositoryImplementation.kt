package com.example.viewmodel.products.Repo

import com.example.themeal_app.DatabaseModel.model.allData
import com.example.viewmodel.network.RemoteDataSource
import retrofit2.Call
import retrofit2.Response

class ProductRepositoryImplementation(var remoteDataSource: RemoteDataSource): mealRepository {
    override suspend fun getCategoryResponse(): Response<allData> {
        return remoteDataSource.getCategoryResponse()
    }

    override suspend fun getMealsByCategory(category: String): Response<allData> {
        return  remoteDataSource.getMealsByCategory(category)
    }

    override suspend fun getMealByName(s: String): Response<allData> {
        return remoteDataSource.getMealByName(s)
    }

    override suspend fun getRandomMeal(): Response<allData> {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun getMealById(id: String): Response<allData> {
        return getMealById(id)
    }

}