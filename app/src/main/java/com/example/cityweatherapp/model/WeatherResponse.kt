package com.example.cityweatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("name") val name: String,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("weather") val weather: ArrayList<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("dt") val dt: Long
)