package com.example.cityweatherapp.main_activity

import com.example.cityweatherapp.model.WeatherResponse

interface MainContract {

    interface Presenter {
        fun onDestroy()

        fun requestDataFromServer()

        fun setCity(city: String)
    }

    interface MainView {

        fun setResult(weatherResponse: WeatherResponse)

        fun onResponseFailure(throwable: Throwable)
    }

    interface GetWeather {
        interface OnFinishedListener {
            fun onFinished(weatherResponse: WeatherResponse)
            fun onFailure(throwable: Throwable)
        }

        fun getWeatherResponse(onFinishedListener: OnFinishedListener, city: String)
    }
}