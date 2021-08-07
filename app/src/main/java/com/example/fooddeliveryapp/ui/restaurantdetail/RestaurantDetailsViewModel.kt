package com.example.fooddeliveryapp.ui.restaurantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.restaurant.Restaurant
import com.example.fooddeliveryapp.model.entity.restaurant.RestaurantResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel@Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {
    fun getRestaurantDetail(id: String): LiveData<Resource<RestaurantResponse>> = apiRepository.getRestaurantById(id)
}