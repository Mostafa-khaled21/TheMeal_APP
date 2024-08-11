package com.example.themeal_app.Data.Repo

import RandomMealResponse
import retrofit2.Response

interface RandomImageRepository {
    suspend fun getRandomMeal(): Response<RandomMealResponse>
}
