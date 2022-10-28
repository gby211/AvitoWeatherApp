package com.pavel.avitoweatherapp.presentation.settings


import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pavel.avitoweatherapp.core.BaseViewModel
import com.pavel.avitoweatherapp.domain.location.LocationTracker
import com.pavel.avitoweatherapp.domain.model.City
import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity
import com.pavel.avitoweatherapp.domain.usecase.GetCoordinatesByCityNameUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KEY_LON = "lon"
private const val KEY_LAT = "lat"
private const val KEY_CITY_NAME = "city"

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCoordinatesByCityNameUC: GetCoordinatesByCityNameUC,
//    private val saveCoordinatesUC: SaveCoordinatesUC,
//    private val getCoordinatesUC: GetCoordinatesUC,
    private val sharedPreferences: SharedPreferences,
    private val locationTracker: LocationTracker
) : BaseViewModel() {


    private val _coordinatesResponse = MutableLiveData<List<City>>(null)
    val coordinatesResponse: LiveData<List<City>> = _coordinatesResponse

    private val _coordinatesByLoc = MutableLiveData<CoordinatesAndCity>(null)
    val coordinatesByLoc: LiveData<CoordinatesAndCity> = _coordinatesByLoc

    private val _isError = MutableLiveData<Boolean>(null)
    val isError: LiveData<Boolean> = _isError

    private val _isSuccess = MutableLiveData<Boolean>(null)
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isNoGeo = MutableLiveData<Boolean>(null)
    val isNoGeo: LiveData<Boolean> = _isNoGeo

    private val _cityName = MutableLiveData<String>(null)
    val cityName: LiveData<String> = _cityName

    private val _coordinates = MutableLiveData<CoordinatesAndCity>(null)
    val coordinates: LiveData<CoordinatesAndCity> = _coordinates

    fun getCoordinatesByCityName() {
        viewModelScope.execute(
            onSuccess = {
                _isSuccess.value = true
            },
            onError = {
                _isError.value = true
            }
        ) {
            Log.d("SettingsVM", _cityName.value.toString())
            _coordinatesResponse.postValue(
                getCoordinatesByCityNameUC.getCoordinatesByCityName(
                    _cityName.value ?: "Москва",
                    "8abc715c188b044ea7f2c6716f2f904f"
                ).body()
            )
//            Log.d("SettingsVM",coordinatesResponse.value?.get(0)?.lon.toString() )
//            saveCoordinatesIntoSha(
//                _coordinatesResponse.value?.get(0)?.lon.toString(),
//                _coordinatesResponse.value?.get(0)?.lat.toString(),
//                _coordinatesResponse.value?.get(0)?.name ?: "Москва",
//            )
        }
    }

    fun getCoordinatesByLocation() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                _coordinatesByLoc.value = CoordinatesAndCity(
                    location.longitude.toString(),
                    location.latitude.toString(),
                    "${location.longitude.toString()}, ${location.latitude.toString()}"
                )
            }
        }
    }

    fun saveCoordByLocIntoSha() {
        sharedPreferences.edit()
            .putString(KEY_LON, _coordinatesByLoc.value?.lon.toString()).apply()
        sharedPreferences.edit()
            .putString(KEY_LAT, _coordinatesByLoc.value?.lat.toString()).apply()
        sharedPreferences.edit()
            .putString(
                KEY_CITY_NAME,
                _coordinatesByLoc.value?.city
            ).apply()
        getCoordinates()
    }

    fun toastComplete() {
        _isNoGeo.value = false
    }

    fun getCoordinates() {
        _coordinates.value = CoordinatesAndCity(
            sharedPreferences.getString(KEY_LON, "") ?: "",
            sharedPreferences.getString(KEY_LAT, "") ?: "",
            sharedPreferences.getString(KEY_CITY_NAME, "") ?: ""
        )
    }


    fun saveCoordinatesIntoSha(/*lon: String, lat: String, city: String*/) {
        sharedPreferences.edit()
            .putString(KEY_LON, _coordinatesResponse.value?.get(0)?.lon.toString()).apply()
        sharedPreferences.edit()
            .putString(KEY_LAT, _coordinatesResponse.value?.get(0)?.lat.toString()).apply()
        sharedPreferences.edit()
            .putString(
                KEY_CITY_NAME,
                _coordinatesResponse.value?.get(0)?.localNames?.ruName ?: "Москва"
            ).apply()
    }

    fun putCityName(cityName: String) {
        _cityName.value = cityName
    }

}