package com.example.fooddeliveryapp.model.entity.order

import com.google.gson.annotations.SerializedName

data class OrderAddRequest(
    @SerializedName("restaurantName")
    val restaurantName: String,
    @SerializedName("mealName")
    val mealName: String,
    @SerializedName("mealImage")
    val mealImage: String,
    @SerializedName("price")
    val price: String
)
