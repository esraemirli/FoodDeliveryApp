package com.example.fooddeliveryapp.ui.restaurantlisting

import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant

interface RestaurantListingAdapterListener {
    fun onRestaurantClickListener(restaurant: Restaurant)
}