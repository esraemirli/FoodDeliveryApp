package com.example.fooddeliveryapp.model.entity

import com.google.gson.annotations.SerializedName

data class Meal(

    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String
)