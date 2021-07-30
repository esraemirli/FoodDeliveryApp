package com.example.fooddeliveryapp.model

import com.example.fooddeliveryapp.network.RetrofitHelper
import retrofit2.Callback

class RemoteDataSource {
    fun getRestaurants(callback: Callback<List<Restaurant>>) {
        RetrofitHelper.service.getRestaurants().enqueue(callback)
    }
}