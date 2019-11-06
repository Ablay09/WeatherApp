package com.example.cityweatherapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton pattern
object ApiClient {
    private const val BASE_URL="https://api.openweathermap.org/"

    //Lazy init
    val apiClient: WeatherApis by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(WeatherApis::class.java)
    }

}