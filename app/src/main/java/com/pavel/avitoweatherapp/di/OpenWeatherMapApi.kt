package com.pavel.avitoweatherapp.di

import com.pavel.avitoweatherapp.data.remote.ApiHelperImpl
import com.pavel.avitoweatherapp.data.remote.WeatherApi
import com.pavel.avitoweatherapp.domain.usecase.GetCoordinatesByCityNameUC
import com.pavel.avitoweatherapp.domain.usecase.GetForecastUC
import com.pavel.avitoweatherapp.domain.util.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO вынести в градл
const val BASE_API_URL = /*BuildConfig.apiBaseUrl*/ "https://api.openweathermap.org/"


@Module
@InstallIn(SingletonComponent::class)
object OpenWeatherMapModule {
    @Provides
    fun providesBaseUrl(): String = BASE_API_URL

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideGetForecastUC(apiHelper: ApiHelperImpl): GetForecastUC =
        GetForecastUC(apiHelper)

    @Provides
    @Singleton
    fun provideGetCoordinatesByCityNameUC(apiHelper: ApiHelperImpl): GetCoordinatesByCityNameUC =
        GetCoordinatesByCityNameUC(apiHelper)
}