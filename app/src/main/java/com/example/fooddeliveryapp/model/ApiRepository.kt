package com.example.fooddeliveryapp.model

import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.local.LocalDataSource
import com.example.fooddeliveryapp.model.remote.AuthRemoteDataSource
import com.example.fooddeliveryapp.model.remote.RemoteDataSource
import com.example.fooddeliveryapp.utils.performAuthTokenNetworkOperation
import com.example.fooddeliveryapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun postRestaurant(restaurantAddRequest: RestaurantAddRequest) =
        performNetworkOperation {
            authRemoteDataSource.postRestaurant(request = restaurantAddRequest)
        }

    fun postMeal(restaurantId : String, mealAddRequest: MealAddRequest) =
        performNetworkOperation {
            authRemoteDataSource.postMeal(restaurantId, request = mealAddRequest)
        }

    fun getRestaurantByCuisine(cuisine : String) =
        performNetworkOperation {
        remoteDataSource.getRestaurantsByCuisine(cuisine)
    }
}
