package com.pavel.avitoweatherapp.presentation.utils.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.pavel.avitoweatherapp.domain.model.Forecast
import com.pavel.avitoweatherapp.domain.model.WeatherCondition

class ForecastCallBack: DiffUtil.ItemCallback<WeatherCondition>() {
    override fun areItemsTheSame(
        oldItem: WeatherCondition,
        newItem: WeatherCondition
    ): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: WeatherCondition, newItem: WeatherCondition): Boolean {
        return oldItem == newItem
    }
}