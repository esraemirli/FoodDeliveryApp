package com.example.fooddeliveryapp.ui.restaurantadd

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddResponse
import com.example.fooddeliveryapp.utils.FirebaseCloudStorageManager
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {


    fun addRestaurant(
        name: String,
        cuisine: String,
        deliveryInfo: String,
        deliveryTime: String,
        imageUrl: Uri?,
        address: String,
        district: String,
        minDeliveryFee: String,
        paymentMethods: String,
        phone: String,
        website: String,
    ): LiveData<Resource<RestaurantAddResponse>>? {


        return if (imageUrl != null) {
            FirebaseCloudStorageManager.uploadImage(imageUrl) {
                val request = RestaurantAddRequest(
                    name, cuisine, deliveryInfo, deliveryTime,
                    it, address, district, minDeliveryFee, paymentMethods, phone, website
                )
                Log.e("Before post restaurant",it)
                Log.e("Request",request.toString())
                apiRepository.postRestaurant(request)
            }
            null
        } else {
            null
        }

    }


}