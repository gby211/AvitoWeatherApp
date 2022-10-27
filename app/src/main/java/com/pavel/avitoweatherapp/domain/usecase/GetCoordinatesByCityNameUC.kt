package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.model.Coordinates
import com.pavel.avitoweatherapp.domain.repositories.ApiRepository
import retrofit2.Response

class GetCoordinatesByCityNameUC(private val apiRepository: ApiRepository) {
    suspend fun getCoordinatesByCityName(cityName:String, apiKey :String): Response<Coordinates> = apiRepository.getCoordinatesByCityName(cityName,apiKey)
}