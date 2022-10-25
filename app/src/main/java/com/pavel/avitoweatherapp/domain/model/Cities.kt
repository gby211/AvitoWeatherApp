package com.pavel.avitoweatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("local_names")
    val localNames: List<String>,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: String
)

data class Cities(
    val cities : List<City>
)