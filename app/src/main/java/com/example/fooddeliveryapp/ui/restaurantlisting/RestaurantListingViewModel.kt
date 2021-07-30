package com.example.fooddeliveryapp.ui.restaurantlisting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.Restaurant

class RestaurantListingViewModel : ViewModel() {

    var restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData()
    private val apiRepository: ApiRepository = ApiRepository()

    fun getResponse() {
        try {
            restaurantList = apiRepository.getRestaurant()
            println("ESRAAA $restaurantList")
        } catch (e: Exception) {
            Log.v("LoginViewModel", e.toString())
        }
    }

}