package com.example.fooddeliveryapp.model.entity.login

import com.example.fooddeliveryapp.model.entity.common.Data
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)