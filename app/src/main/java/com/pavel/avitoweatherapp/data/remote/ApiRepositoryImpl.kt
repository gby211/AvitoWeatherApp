package com.pavel.avitoweatherapp.data.remote

import android.util.Log
import com.pavel.avitoweatherapp.domain.model.City
import com.pavel.avitoweatherapp.domain.model.Forecast
import com.pavel.avitoweatherapp.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi) : ApiRepository {

    override suspend fun getForecast(lat: String, lon: String, apiKey: String): Response<Forecast> {
        val resp = weatherApi.getForecast(lat = lat, lon = lon, apiKey = apiKey)
        Log.d("ggs", resp.message())
        return resp
    }

    override suspend fun getCoordinatesByCityName(
        cityName: String,
        apiKey: String
    ): /*Response<Cities>*///FIXME
            Response<List<City>> {
        return weatherApi.getCoordinatesByCityName(cityName, apiKey = apiKey)
    }
}