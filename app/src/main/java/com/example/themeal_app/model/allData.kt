package com.example.themeal_app.model

import Category
import Meal
import MealDetail

data class allData(
    val categories: List<Category>,
    val meals: List<Meal>,
    val mealsDetail: List<MealDetail>
)