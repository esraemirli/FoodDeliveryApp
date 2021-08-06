package com.example.fooddeliveryapp.model.entity.restaurant

import com.example.fooddeliveryapp.model.entity.Meal
import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("deliveryInfo")
    val deliveryInfo: String,
    @SerializedName("deliveryTime")
    val deliveryTime: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("minimumDeliveryFee")
    val minimumDeliveryFee: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("paymentMethods")
    val paymentMethods: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String
)

