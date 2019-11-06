package com.example.cityweatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather (
    @SerializedName("description") val description: String
)