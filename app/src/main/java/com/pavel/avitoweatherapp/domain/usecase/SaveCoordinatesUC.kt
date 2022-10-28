package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity
import com.pavel.avitoweatherapp.domain.repositories.DataRepository

class SaveCoordinatesUC(private val dataRepository: DataRepository) {
    fun saveCoordinates(coordinatesAndCity: CoordinatesAndCity) =
        dataRepository.saveCoordinates(coordinatesAndCity)
}