package com.pavel.avitoweatherapp.data.local.sharedpref

import android.content.Context
import android.preference.PreferenceManager
import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_LON = "lon"
private const val KEY_LAT = "lat"
private const val KEY_CITY_NAME = "city"

@Singleton
class SharedPrefUserStorage @Inject constructor(@ApplicationContext context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)


    fun saveCoordinates(coordinates: CoordinatesAndCity): Boolean {
        prefs.edit().putString(KEY_LON, coordinates.lon).apply()
        prefs.edit().putString(KEY_LAT, coordinates.lat).apply()
        prefs.edit().putString(KEY_CITY_NAME, coordinates.city).apply()
        return true
    }

    fun getCoordinates(): CoordinatesAndCity {
        val lon = prefs.getString(KEY_LON, "") ?: ""
        val lat = prefs.getString(KEY_LAT, "") ?: ""
        val city = prefs.getString(KEY_CITY_NAME, "") ?: ""
        return CoordinatesAndCity(lon = lon, lat = lat, city = city)
    }
}