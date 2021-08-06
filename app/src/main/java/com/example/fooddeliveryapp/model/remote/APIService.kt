package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.login.LoginResponse
import com.example.fooddeliveryapp.model.entity.restaurant.RestaurantListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("/a/api/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


}