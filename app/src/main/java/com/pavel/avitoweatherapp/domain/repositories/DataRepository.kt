package com.pavel.avitoweatherapp.domain.repositories

import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity

interface DataRepository {
    fun saveCoordinates(coordinates: CoordinatesAndCity): Boolean
    fun getCoordinatesAndCityName(): CoordinatesAndCity
}