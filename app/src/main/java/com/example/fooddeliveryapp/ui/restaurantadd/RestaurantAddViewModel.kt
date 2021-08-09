package com.example.fooddeliveryapp.ui.restaurantadd

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddResponse
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
    ): LiveData<Resource<RestaurantAddResponse>> {
        var uploadImageUrl =
            "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/error%2F404.png?alt=media&token=5fbd589f-519f-4f1f-b6e5-d4440c51b013"
//        if(imageUrl !=null) {
//            uploadImageUrl= FirebaseCloudStorageManager.uploadImage(imageUrl)
//        }

        val request = RestaurantAddRequest(
            name, cuisine, deliveryInfo, deliveryTime,
            uploadImageUrl, address, district, minDeliveryFee, paymentMethods, phone, website
        )
        return apiRepository.postRestaurant(request)


    }


}