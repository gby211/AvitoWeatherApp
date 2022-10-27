package com.pavel.avitoweatherapp.data.local.sharedpref

import com.pavel.avitoweatherapp.data.remote.WeatherApi
import com.pavel.avitoweatherapp.domain.model.Coordinates
import javax.inject.Inject

//class DataRepositoryImpl @Inject constructor(private val sharedPrefUserStorage: SharedPrefUserStorage): DataRepository {
//    override fun saveCoordinates(coordinates: Coordinates): Boolean {
//        return sharedPrefUserStorage.saveCoordinates(coordinates)
//    }
//
//    override fun getCoordinates(): Coordinates {
//        return sharedPrefUserStorage.getCoordinates()
//    }
//}