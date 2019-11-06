package com.example.cityweatherapp.network

import com.example.cityweatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApis {
    @GET("data/2.5/weather?units=metric")
    fun getWeatherForecast(
        @Query("q") q: String,
        @Query("appid") appid: String
    ) : Call<WeatherResponse>
}