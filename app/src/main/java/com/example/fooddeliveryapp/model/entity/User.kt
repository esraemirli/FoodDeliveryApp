package com.example.fooddeliveryapp.model.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
)
