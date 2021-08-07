package com.example.fooddeliveryapp.model

import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.local.LocalDataSource
import com.example.fooddeliveryapp.model.remote.RemoteDataSource
import com.example.fooddeliveryapp.utils.performAuthTokenNetworkOperation
import com.example.fooddeliveryapp.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
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

//    fun register(registerRequest: RegisterRequest) =
//        performNetworkOperation {
//            remoteDataSource.postRegister(request = registerRequest)
//        }

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantByCuisine(cuisine : String) =
        performNetworkOperation {
        remoteDataSource.getRestaurantsByCuisine(cuisine)
    }
}
