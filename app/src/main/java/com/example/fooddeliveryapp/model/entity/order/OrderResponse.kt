package com.example.fooddeliveryapp.model.entity.order

import com.example.fooddeliveryapp.model.entity.Order
import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val orderList: List<Order>,
    val success: Boolean
)