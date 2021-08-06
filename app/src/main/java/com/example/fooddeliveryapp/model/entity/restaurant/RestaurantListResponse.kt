package com.example.fooddeliveryapp.model.entity.restaurant

data class RestaurantListResponse(
    val count: Int,
    val `data`: List<Restaurant>,
    val success: Boolean
)