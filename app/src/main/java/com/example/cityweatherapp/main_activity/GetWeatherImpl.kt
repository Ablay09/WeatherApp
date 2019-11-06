package com.example.cityweatherapp.main_activity

import com.example.cityweatherapp.model.Constants
import com.example.cityweatherapp.model.WeatherResponse
import com.example.cityweatherapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetWeatherImpl : MainContract.GetWeather{
    override fun getWeatherResponse(onFinishedListener: MainContract.GetWeather.OnFinishedListener, city: String) {
        ApiClient.apiClient.getWeatherForecast(city, Constants.API).enqueue(
            object: Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    onFinishedListener.onFailure(throwable = t)
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    onFinishedListener.onFinished(response.body()!!)
                }
            })
    }
}