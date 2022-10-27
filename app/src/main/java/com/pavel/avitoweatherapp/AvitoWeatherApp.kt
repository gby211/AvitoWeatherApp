package com.pavel.avitoweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AvitoWeatherApp : Application() {

    companion object {
        private lateinit var instance: AvitoWeatherApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}