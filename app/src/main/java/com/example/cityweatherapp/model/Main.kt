package com.example.cityweatherapp.model

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("temp") val temp: Double
)