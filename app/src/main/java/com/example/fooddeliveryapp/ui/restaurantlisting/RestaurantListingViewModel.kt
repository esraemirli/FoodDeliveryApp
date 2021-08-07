package com.example.fooddeliveryapp.ui.restaurantlisting

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.restaurantlisting.RestaurantResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListingViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurants(): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurants()

    fun getRestaurantByCuisine(cuisine : String): LiveData<Resource<RestaurantResponse>> =
        apiRepository.getRestaurantByCuisine(cuisine)


}