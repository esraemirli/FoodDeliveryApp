package com.example.fooddeliveryapp.ui.restaurantadd

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddRequest
import com.example.fooddeliveryapp.model.entity.restaurantadd.RestaurantAddResponse
import com.example.fooddeliveryapp.utils.Resource
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RestaurantAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var storageRef: StorageReference = storage.reference

    fun addRestaurant(
        name: String,
        cuisine: String,
        deliveryInfo: String,
        deliveryTime: String,
        imageUrl: String,
        address: String,
        district: String,
        minDeliveryFee: String,
        paymentMethods: String,
        phone: String,
        website: String,
    ): LiveData<Resource<RestaurantAddResponse>> {
        val request = RestaurantAddRequest(name, cuisine,deliveryInfo, deliveryTime,
                        imageUrl,address, district, minDeliveryFee, paymentMethods, phone, website)
        return apiRepository.postRestaurant(request)
    }

    fun uploadImage(selectedImage : Uri) : String{
        val ref = storageRef.child("images/" + UUID.randomUUID().toString())
        val uploadTask = ref.putFile(selectedImage)
        var downloadUri = ""

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            Log.e("downloadUrl", "task is successful = ${task.isSuccessful}")
            downloadUri = if (task.isSuccessful) {
                ref.downloadUrl.toString()
            } else {
                ""
            }
        }

        Log.e("downloadUrl", "IMAGE URL => $downloadUri")

        return downloadUri
    }

}