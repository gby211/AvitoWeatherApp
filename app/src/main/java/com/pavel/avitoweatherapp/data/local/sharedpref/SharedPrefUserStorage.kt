package com.pavel.avitoweatherapp.data.local.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.pavel.avitoweatherapp.domain.model.Coordinates
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_LON = "lon"
private const val KEY_LAT = "lat"

//@Singleton
//class SharedPrefUserStorage {
//
//     fun saveCoordinates(coordinates: Coordinates): Boolean {
//        sharedPreferences.edit().putString(KEY_LON, coordinates.lon).apply()
//        sharedPreferences.edit().putString(KEY_LAT, coordinates.lat).apply()
//        return true
//    }
//
//     fun getCoordinates(): Coordinates {
//        val lon = sharedPreferences.getString(KEY_LON, "") ?: ""
//        val lat = sharedPreferences.getString(KEY_LAT, "") ?: ""
//        return Coordinates(lon = lon, lat = lat)
//    }
//}