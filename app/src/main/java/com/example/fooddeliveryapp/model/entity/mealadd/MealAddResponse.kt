package com.example.fooddeliveryapp.model.entity.mealadd

import com.example.fooddeliveryapp.model.entity.common.Data
import com.google.gson.annotations.SerializedName

data class MealAddResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)
