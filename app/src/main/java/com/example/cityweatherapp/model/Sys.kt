package com.example.cityweatherapp.model

import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("country") val country: String
)