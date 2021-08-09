package com.example.fooddeliveryapp.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.fooddeliveryapp.model.entity.User

class SettingViewModel : ViewModel() {
    fun getUser() : LiveData<Resource<User>>? {
        return null
    }

    fun updateUser(userRequest: User) : LiveData<Resource<Boolean>>?  {
        return null
    }
}