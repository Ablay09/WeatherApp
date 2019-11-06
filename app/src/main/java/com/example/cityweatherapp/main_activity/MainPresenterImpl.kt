package com.example.cityweatherapp.main_activity

import com.example.cityweatherapp.model.WeatherResponse

class MainPresenterImpl : MainContract.Presenter, MainContract.GetWeather.OnFinishedListener {

    private var mainView: MainContract.MainView?
    private var getWeather: MainContract.GetWeather
    private lateinit var city: String

    constructor(mainView: MainContract.MainView, getWeather: MainContract.GetWeather) {
        this.mainView = mainView
        this.getWeather = getWeather
    }

    override fun onDestroy() {
        mainView = null
    }

    override fun requestDataFromServer() {
        getWeather.getWeatherResponse(this, city)
    }

    override fun setCity(city: String) {
        this.city = city
    }

    override fun onFinished(weatherResponse: WeatherResponse) {
        mainView.let{
            it?.setResult(weatherResponse)
        }
    }

    override fun onFailure(throwable: Throwable) {
        mainView.let {
            it?.onResponseFailure(throwable)
        }
    }
}