package com.example.fooddeliveryapp.ui.restaurantlisting

import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant

interface IRestaurantOnClick {
    fun onClick(restaurant: Restaurant)
}