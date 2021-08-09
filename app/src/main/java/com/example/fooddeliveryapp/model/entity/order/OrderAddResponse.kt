package com.example.fooddeliveryapp.model.entity.order

import com.example.fooddeliveryapp.model.entity.mealadd.Message
import com.google.gson.annotations.SerializedName

data class OrderAddResponse(
    @SerializedName("message")
    val message: Message,
    @SerializedName("success")
    val success: Boolean
)
