package com.example.fooddeliveryapp.model
import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("ingredient")
    var ingredient: String,
    @SerializedName("includes")
	var includes: Boolean

)