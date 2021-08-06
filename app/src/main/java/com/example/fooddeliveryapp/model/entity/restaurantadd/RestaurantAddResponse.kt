package com.example.fooddeliveryapp.model.entity.restaurantadd

import com.example.fooddeliveryapp.model.entity.common.Data
import com.google.gson.annotations.SerializedName

data class RestaurantAddResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)
