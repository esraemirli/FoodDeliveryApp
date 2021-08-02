package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.Restaurant
import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}