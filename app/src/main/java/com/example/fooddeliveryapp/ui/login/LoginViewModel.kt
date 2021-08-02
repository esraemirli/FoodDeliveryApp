package com.example.fooddeliveryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.login.LoginRequest
import com.example.fooddeliveryapp.model.entity.login.LoginResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    fun login(email: String, password: String): LiveData<Resource<LoginResponse>> {
        val request = LoginRequest(email, password)
        return apiRepository.login(request)
    }
}