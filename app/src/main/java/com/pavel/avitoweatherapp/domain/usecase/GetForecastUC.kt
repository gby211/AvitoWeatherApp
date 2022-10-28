package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.model.Forecast
import com.pavel.avitoweatherapp.domain.repositories.ApiRepository
import retrofit2.Response

class GetForecastUC(private val apiRepository: ApiRepository) {
    suspend fun getForecast(lat: String, lon: String, apiKey: String): Response<Forecast> =
        apiRepository.getForecast(lat, lon, apiKey)
}