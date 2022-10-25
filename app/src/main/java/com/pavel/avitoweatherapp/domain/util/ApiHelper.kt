package com.pavel.avitoweatherapp.domain.util

import com.pavel.avitoweatherapp.domain.model.Forecast
import retrofit2.Response

interface ApiHelper {
    suspend fun getForecast(lat:String, lon :String, apiKey :String): Response<Forecast>
    suspend fun getCoordinatesByCityName(): Response<Forecast>
}
