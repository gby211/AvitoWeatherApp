package com.pavel.avitoweatherapp.presentation.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pavel.avitoweatherapp.core.BaseViewModel
import com.pavel.avitoweatherapp.domain.model.CoordinatesAndCity
import com.pavel.avitoweatherapp.domain.model.WeatherCondition
import com.pavel.avitoweatherapp.domain.usecase.GetCoordinatesByCityNameUC
import com.pavel.avitoweatherapp.domain.usecase.GetForecastUC
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val KEY_LON = "lon"
private const val KEY_LAT = "lat"
private const val KEY_CITY_NAME = "city"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoordinatesByCityNameUC: GetCoordinatesByCityNameUC,
//    private val getCoordinatesUC: GetCoordinatesUC,
    private val getForecastUC: GetForecastUC,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _forecastResponse = MutableLiveData<List<WeatherCondition>>(null)
    val forecastResponse: LiveData<List<WeatherCondition>> = _forecastResponse

    private val _isError = MutableLiveData<Boolean>(null)
    val isError: LiveData<Boolean> = _isError

    private val _isSuccess = MutableLiveData<Boolean>(null)
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _cityName = MutableLiveData<String>(null)
    val cityName: LiveData<String> = _cityName

    fun loadForecast() {
        viewModelScope.execute(
            onSuccess = {
                _isSuccess.value = true
            },
            onError = {
                _isError.value = true
            }
        ) {
            val coordinates = CoordinatesAndCity(
                sharedPreferences.getString(KEY_LON, "") ?: "", sharedPreferences.getString(
                    KEY_LAT, ""
                ) ?: "", sharedPreferences.getString(
                    KEY_CITY_NAME, ""
                ) ?: ""
            )
            Log.d("coordinatesVM", coordinates.toString())
            if (coordinates != CoordinatesAndCity("", "", "")) {
                _cityName.postValue(coordinates.city)
                _forecastResponse.postValue(
                    getForecastUC.getForecast(
                        coordinates.lat,
                        coordinates.lon,
                        "8abc715c188b044ea7f2c6716f2f904f"
                    ).body()?.listWeather
                )
            } else {
                _forecastResponse.postValue(
                    getForecastUC.getForecast(
                        "55.7504461",
                        "37.617494",
                        "8abc715c188b044ea7f2c6716f2f904f"
                    ).body()?.listWeather
                )
                Log.d(
                    "Coordinates",
                    getForecastUC.getForecast(
                        "55.7504461",
                        "37.617494",
                        "8abc715c188b044ea7f2c6716f2f904f"
                    ).body()?.listWeather.toString()
                )
            }
        }
    }

}