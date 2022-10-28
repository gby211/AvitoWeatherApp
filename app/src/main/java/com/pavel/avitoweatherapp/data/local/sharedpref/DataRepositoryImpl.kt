package com.pavel.avitoweatherapp.data.local.sharedpref

import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity
import com.pavel.avitoweatherapp.domain.repositories.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val sharedPrefUserStorage: SharedPrefUserStorage) :
    DataRepository {
    override fun saveCoordinates(coordinates: CoordinatesAndCity): Boolean {
        return sharedPrefUserStorage.saveCoordinates(coordinates)
    }

    override fun getCoordinatesAndCityName(): CoordinatesAndCity {
        return sharedPrefUserStorage.getCoordinates()
    }
}