package com.pavel.avitoweatherapp.domain.usecase

import com.pavel.avitoweatherapp.domain.util.ApiHelper

class GetCoordinatesByCityNameUC(private val apiHelper: ApiHelper) {
    suspend fun getCoordinatesByCityName() = apiHelper.getCoordinatesByCityName()
}