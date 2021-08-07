package com.example.fooddeliveryapp.model.entity.meal

import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant

data class MealResponse(
    val `data`: Meal,
    val success: Boolean
)
