package com.pavel.avitoweatherapp.data.remote


import com.pavel.avitoweatherapp.domain.model.City
import com.pavel.avitoweatherapp.domain.model.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.openweathermap.org/

interface WeatherApi {

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<Forecast>

    @GET("geo/1.0/direct")
    suspend fun getCoordinatesByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("limit") limit: String = "1"
    ): Response<List<City>>
}
