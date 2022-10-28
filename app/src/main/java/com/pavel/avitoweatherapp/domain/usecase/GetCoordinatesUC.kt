package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.repositories.DataRepository

class GetCoordinatesUC(private val dataRepository: DataRepository) {
    fun getCoordinates() = dataRepository.getCoordinatesAndCityName()
}