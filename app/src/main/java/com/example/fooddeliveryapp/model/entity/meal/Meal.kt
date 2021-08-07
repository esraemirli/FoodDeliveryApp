package com.example.fooddeliveryapp.model.entity.meal

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("_id")
    val id: String,
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