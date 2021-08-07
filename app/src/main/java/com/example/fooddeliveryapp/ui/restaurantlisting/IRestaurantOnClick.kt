package com.example.fooddeliveryapp.ui.restaurantlisting

import com.example.fooddeliveryapp.model.entity.Restaurant

interface IRestaurantOnClick {
    fun onClick(restaurant: Restaurant)
}