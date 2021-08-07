package com.example.fooddeliveryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.register.RegisterRequest
import com.example.fooddeliveryapp.model.entity.register.RegisterResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {

    fun register(name: String, email: String, password: String): LiveData<Resource<RegisterResponse>>{
        val request = RegisterRequest(name, email, password)
        return apiRepository.register(request)
    }
}