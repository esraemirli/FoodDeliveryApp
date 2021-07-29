package com.example.fooddeliveryapp.model

data class Restaurant(
    var id : Long,
    var name: String,
    var location: String,
    var image: String? = "https://c.ndtvimg.com/2020-04/dih4ifhg_pasta_625x300_22_April_20.jpg"
)
