package com.example.fooddeliveryapp.model.remote

import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    //Upload file and multipart example
//    suspend fun uploadFile(file: File) =
//        getResult {
//            apiService.uploadFile(
//                file.asRequestBody("image/*".toMediaTypeOrNull()),
//                "Hello Name".toRequestBody("text/plain".toMediaTypeOrNull()),
//                "Hello Name".toRequestBody("text/plain".toMediaTypeOrNull())
//            )
//        }


}
