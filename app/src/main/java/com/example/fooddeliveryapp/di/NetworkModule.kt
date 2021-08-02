package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.BuildConfig
import com.example.fooddeliveryapp.model.remote.APIService
import com.example.fooddeliveryapp.model.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    /*@Provides
    @TunahanEndPoint
    fun provideApiString(): String {
        return "https://dist-learn.herokuapp.com/api/"
    }*/

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService,
//        rickApiService: RickApiService
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://dist-learn.herokuapp.com/api/")
    }
}

data class EndPoint(val url: String)