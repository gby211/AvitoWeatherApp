package com.pavel.avitoweatherapp.presentation.main

import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavel.avitoweatherapp.databinding.ItemForcastDayBinding
import com.pavel.avitoweatherapp.databinding.ItemForecastWeekBinding
import com.pavel.avitoweatherapp.domain.model.WeatherCondition
import com.pavel.avitoweatherapp.presentation.utils.diffcallback.ForecastCallBack

class ForecastWeekListAdapter :
    ListAdapter<WeatherCondition, ForecastWeekListAdapter.ForecastViewHolder>(ForecastCallBack()) {

    inner class ForecastViewHolder(private val binding: ItemForecastWeekBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(receivedItem: WeatherCondition) {
            binding.apply {
                textTemp.text = "${receivedItem.main.temp.toString()/*TODO*/}℃"
                textMaxTemp.text = "${receivedItem.main.tempMax.toString()/*TODO*/}℃"
                textMinTemp.text = "${receivedItem.main.tempMin.toString()/*TODO*/}℃"

//                val date = LocalDateTime.parse(receivedItem.dt.toString(), DateTimeFormatter.ISO_DATE_TIME)
//                date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
                textTime.text = DateUtils.getRelativeTimeSpanString(receivedItem.dt.toLong())
//                textTime.text = receivedItem.dt.toString() //TODO

                Glide.with(itemView.context)
                    .load("http://openweathermap.org/img/wn/${receivedItem.weather[0].icon}@2x.png")
                    .fitCenter()
                    .into(imageViewWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastWeekBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}