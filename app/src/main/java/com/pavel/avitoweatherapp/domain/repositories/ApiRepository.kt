package com.pavel.avitoweatherapp.domain.repositories

import com.pavel.avitoweatherapp.domain.model.Coordinates
import com.pavel.avitoweatherapp.domain.model.Forecast
import retrofit2.Response

interface ApiRepository {
    suspend fun getForecast(lat:String, lon :String, apiKey :String): Response<Forecast>
    suspend fun getCoordinatesByCityName(cityName : String, apiKey: String): Response<Coordinates>
}