package com.example.fooddeliveryapp.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.ApiRepository
import com.example.fooddeliveryapp.model.entity.User
import com.example.fooddeliveryapp.model.entity.profile.UserRequest
import com.example.fooddeliveryapp.model.entity.profile.UserResponse
import com.example.fooddeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    companion object {
        private const val IMG_URL_START =
            "https://firebasestorage.googleapis.com/v0/b/fooddeliveryapp-fe5bf.appspot.com/o/avatars%2F"
        private const val IMG_URL_END = ".png?alt=media&token=08d1ac8b-a039-4335-85f9-9805f993bf71"
    }

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()

    fun updateUser(userRequest: UserRequest): LiveData<Resource<User>> =
        apiRepository.updateUser(userRequest)

    fun getAvatarId(url: String): Int {
        val result = url.substringAfter(IMG_URL_START).substringBefore(IMG_URL_END)
        return result.toInt()
    }

    fun getImageUrl(id: Int): String {
        return "$IMG_URL_START${id}$IMG_URL_END"
    }

}