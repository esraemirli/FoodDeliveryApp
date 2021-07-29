package com.example.fooddeliveryapp.utils

import com.example.fooddeliveryapp.model.Restaurant

interface RestaurantListingAdapterListener {
    fun onRestaurantClickListener(restaurant: Restaurant)
}