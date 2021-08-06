package com.example.fooddeliveryapp.ui.mealadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddRequest
import com.example.fooddeliveryapp.model.entity.mealadd.MealAddResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun addMeal (
        restaurantId : String,
      name : String,
      imageUrl : String,
      price : String
    ) : LiveData<Resource<MealAddResponse>> {
        val request = MealAddRequest(name, imageUrl, price)
        return apiRepository.postMeal(restaurantId, request)
    }

}