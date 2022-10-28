package com.pavel.avitoweatherapp.di

import android.content.Context
import android.content.SharedPreferences
import com.pavel.avitoweatherapp.data.remote.ApiRepositoryImpl
import com.pavel.avitoweatherapp.data.remote.WeatherApi
import com.pavel.avitoweatherapp.domain.repositories.ApiRepository
import com.pavel.avitoweatherapp.domain.usecase.GetCoordinatesByCityNameUC
import com.pavel.avitoweatherapp.domain.usecase.GetForecastUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// TODO вынести в градл
const val BASE_API_URL = /*BuildConfig.apiBaseUrl*/ "http://api.openweathermap.org/"
private const val SHA = "sha"

@Module
@InstallIn(SingletonComponent::class)
object OpenWeatherModule {
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
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

//    @Singleton
//    @Provides
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
//        return context.getSharedPreferences(SHA, Context.MODE_PRIVATE)
//    }

//    @Provides
//    @Singleton
//    fun provideGetCoordinatesUC(dataRepository: DataRepository): GetCoordinatesUC =
//        GetCoordinatesUC(dataRepository)
//
//    @Provides
//    @Singleton
//    fun provideSaveCoordinatesUC(dataRepository: DataRepository): SaveCoordinatesUC =
//        SaveCoordinatesUC(dataRepository)

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHA, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiRepository: ApiRepositoryImpl): ApiRepository = apiRepository

//    @Provides
//    @Singleton
//    fun provideDataRepository(dataRepository: DataRepositoryImpl): DataRepository = dataRepository

    @Provides
    @Singleton
    fun provideGetForecastUC(apiRepository: ApiRepositoryImpl): GetForecastUC =
        GetForecastUC(apiRepository)

    @Provides
    @Singleton
    fun provideGetCoordinatesByCityNameUC(apiRepository: ApiRepositoryImpl): GetCoordinatesByCityNameUC =
        GetCoordinatesByCityNameUC(apiRepository)
}