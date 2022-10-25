package com.pavel.avitoweatherapp.data.remote

import com.pavel.avitoweatherapp.domain.model.Forecast
import com.pavel.avitoweatherapp.domain.util.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val weatherApi: WeatherApi) : ApiHelper {

    override suspend fun getForecast(lat: String, lon: String, apiKey: String): Response<Forecast> {
        return weatherApi.getForecast(lat = lat, lon = lon, apiKey = apiKey)
    }

    override suspend fun getCoordinatesByCityName(): Response<Forecast> {
        TODO("Not yet implemented")
    }
}