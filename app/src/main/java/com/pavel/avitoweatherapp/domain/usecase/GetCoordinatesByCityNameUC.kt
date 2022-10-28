package com.pavel.avitoweatherapp.domain.usecase


import com.pavel.avitoweatherapp.domain.model.City
import com.pavel.avitoweatherapp.domain.repositories.ApiRepository
import retrofit2.Response

class GetCoordinatesByCityNameUC(private val apiRepository: ApiRepository) {
    suspend fun getCoordinatesByCityName(
        cityName: String,
        apiKey: String
    ): /*Response<Cities>*///FIXME
            Response<List<City>> = apiRepository.getCoordinatesByCityName(cityName, apiKey)
}