package com.example.fooddeliveryapp.network

import com.example.fooddeliveryapp.utils.SharedPreferencesUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface RetrofitResponseHandler {
    fun onResponse(response: APIService)
    fun onError()
}

object RetrofitHelper {

    private val okhttp: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            val token = SharedPreferencesUtil.getToken()
            val request = it.request().newBuilder().addHeader("X-Token", token).build()
            it.proceed(request)
        })
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        )

        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://fooddeliveryapp-fe5bf-default-rtdb.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp)
        .build()

    var service: APIService = retrofit.create(APIService::class.java)

}