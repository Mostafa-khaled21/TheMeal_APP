package com.example.themeal_app.DatabaseModel.model

import Category
import MealDetail

data class allData(
    val categories: List<Category>,
    val meals: List<Meal>,
    val mealsDetail: List<MealDetail>
)