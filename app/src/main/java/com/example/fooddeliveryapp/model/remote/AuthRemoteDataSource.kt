package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.order.OrderAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.utils.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthAPIService) : BaseDataSource() {
    suspend fun postRestaurant(request: RestaurantAddRequest) = getResult {
        authAPIService.postRestaurant(request)
    }

    suspend fun postMeal(restaurantId : String,request: MealAddRequest) = getResult {
        authAPIService.postMeal(restaurantId, request)
    }

    suspend fun postOrder(orderAddRequest: OrderAddRequest) = getResult {
        authAPIService.postOrder(orderAddRequest)
    }
}