package com.example.themeal_app.Data.Repo

import com.example.themeal_app.DatabaseModel.model.allData
import retrofit2.Response

interface RandomImageRepository {
    suspend fun getRandomMeal(): Response<allData>
}
