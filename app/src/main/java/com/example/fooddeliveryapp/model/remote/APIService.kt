package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.login.LoginResponse
import com.example.fooddeliveryapp.model.entity.restaurant.RestaurantListResponse
import com.example.fooddeliveryapp.model.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path

interface APIService {
    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @GET("a/restaurant")
    suspend fun getRestaurantsByCuisine(@Query("cuisineName") cuisine : String): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


}