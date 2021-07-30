package com.example.fooddeliveryapp.model

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository {

    val remoteDataSource = RemoteDataSource()

    fun getRestaurant(): MutableLiveData<List<Restaurant>> {
        val restaurantLiveData = MutableLiveData<List<Restaurant>>()
        remoteDataSource.getRestaurants(callback = object :
            Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        restaurantLiveData.value = response.body()
                    }
                } else {
                    throw Exception("Wrong Credentials")
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                throw Exception("Service Error ${t.message}")
            }
        })
        return restaurantLiveData
    }
}