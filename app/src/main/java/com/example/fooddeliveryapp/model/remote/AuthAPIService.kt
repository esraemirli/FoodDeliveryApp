package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.User
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddResponse
import com.example.fooddeliveryapp.model.entity.order.OrderAddRequest
import com.example.fooddeliveryapp.model.entity.order.OrderAddResponse
import com.example.fooddeliveryapp.model.entity.order.OrderResponse
import com.example.fooddeliveryapp.model.entity.profile.UserRequest
import com.example.fooddeliveryapp.model.entity.profile.UserResponse
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthAPIService {

    @POST("a/restaurant")
    suspend fun postRestaurant(@Body request: RestaurantAddRequest): Response<RestaurantAddResponse>

    @POST("a/restaurant/{restaurantId}/meal")
    suspend fun postMeal(
        @Path("restaurantId") restaurantId: String,
        @Body request: MealAddRequest
    ): Response<MealAddResponse>

    @POST("a/order")
    suspend fun postOrder(@Body request: OrderAddRequest): Response<OrderAddResponse>

    @GET("a/order")
    suspend fun getOrders(): Response<OrderResponse>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body request : UserRequest) : Response<User>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>

}