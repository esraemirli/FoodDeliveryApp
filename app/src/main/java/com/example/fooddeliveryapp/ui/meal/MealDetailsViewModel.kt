package com.example.fooddeliveryapp.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.meal.MealResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel(){
    fun getMealDetails (id: String): LiveData<Resource<MealResponse>> = apiRepository.getMealById(id)
}