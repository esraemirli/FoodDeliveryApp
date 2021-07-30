package com.example.fooddeliveryapp.network

import com.example.fooddeliveryapp.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("restaurants.json")
    fun getRestaurants(): Call<List<Restaurant>>

    // TODO Map<Map<String, Object>> şeklinde bakılacak -- Realtime
    // TODO Store'u list olarak almaya bak..
    
}