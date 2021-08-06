package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.Restaurant
import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.login.LoginResponse
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddResponse
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): Response<List<Restaurant>>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("restaurant")
    suspend fun postRestaurant(@Body request: RestaurantAddRequest) : Response<RestaurantAddResponse>

    @POST("restaurant/{restaurantId}/meal")
    suspend fun postMeal(@Path("restaurantId") restaurantId : String, @Body request: MealAddRequest) : Response<MealAddResponse>
}