package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.util.ApiHelper

class GetForecastUC(private val apiHelper: ApiHelper) {
    suspend fun getForecast(lat:String, lon :String, apiKey :String) = apiHelper.getForecast(lat, lon, apiKey)
}