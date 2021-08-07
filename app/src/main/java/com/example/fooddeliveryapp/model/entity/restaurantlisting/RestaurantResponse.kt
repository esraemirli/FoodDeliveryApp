package com.example.fooddeliveryapp.model.entity.restaurantlisting


import com.example.fooddeliveryapp.model.entity.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val `data`: List<Restaurant>,
    @SerializedName("success")
    val success: Boolean
)