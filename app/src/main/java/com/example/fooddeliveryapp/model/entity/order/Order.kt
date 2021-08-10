package com.example.fooddeliveryapp.model.entity.order

import com.example.fooddeliveryapp.model.entity.meal.Meal
import com.example.fooddeliveryapp.model.entity.restaurant.OrderRestaurant
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.google.gson.annotations.SerializedName
import java.util.*

data class Order(
    @SerializedName("_id")
    val id: String,
    @SerializedName("createdDate")
    val createdDate: Date,
    @SerializedName("meal")
    val meal: Meal,
    @SerializedName("restaurant")
    val restaurant: OrderRestaurant,
    @SerializedName("price")
    val price: Double
)
