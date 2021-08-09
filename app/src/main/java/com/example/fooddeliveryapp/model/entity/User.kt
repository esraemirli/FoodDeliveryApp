package com.example.fooddeliveryapp.model.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("paymentCard")
    val paymentCard: Int,
)
