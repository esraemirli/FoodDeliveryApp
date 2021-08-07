package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }
    suspend fun getRestaurantsByCuisine(cuisine : String) = getResult { apiService.getRestaurantsByCuisine(cuisine) }

    suspend fun getRestaurantById(id: String) = getResult { apiService.getRestaurantById(id) }

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }



}

