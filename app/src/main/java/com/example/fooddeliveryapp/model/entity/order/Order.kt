package com.example.fooddeliveryapp.model.entity.order

import com.google.gson.annotations.SerializedName
import java.util.*

data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("createdDate")
    val createdDate: Date,
    @SerializedName("restaurantName")
    val restaurantName: String,
    @SerializedName("mealName")
    val mealName: String,
    @SerializedName("mealImage")
    val mealImage: String,
    @SerializedName("price")
    val price: Double
)
