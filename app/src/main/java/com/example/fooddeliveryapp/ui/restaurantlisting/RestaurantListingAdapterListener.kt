package com.example.fooddeliveryapp.ui.restaurantlisting

import com.example.fooddeliveryapp.model.entity.Restaurant

interface RestaurantListingAdapterListener {
    fun onRestaurantClickListener(restaurant: Restaurant)
}