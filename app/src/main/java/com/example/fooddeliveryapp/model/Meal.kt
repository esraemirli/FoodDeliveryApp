package com.example.fooddeliveryapp.model

import com.google.gson.annotations.SerializedName

data class Meal (

	@SerializedName("description")
	val description : String,
	@SerializedName("image")
	val image : String,
	@SerializedName("ingredients")
	val ingredients : List<Ingredient>,
	@SerializedName("name")
	val name : String,
	@SerializedName("price")
	val price : Double
)