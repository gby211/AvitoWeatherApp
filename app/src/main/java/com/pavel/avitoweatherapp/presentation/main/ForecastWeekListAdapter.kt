package com.pavel.avitoweatherapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

//                val date = LocalDateTime.parse(receivedItem.dtTxt, DateTimeFormatter.ISO_DATE_TIME)
                val date = receivedItem.dtTxt
                textTime.text =
                    "${if (date[8] != '0') date[8] else ""}${date[9]} - ${if (date[5] != '0') date[5] else ""}${date[6]}"
//                    DateUtils.formatDateTime(textTime.context,/*receivedItem.dt.toLong()*/date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli(),DateUtils.FORMAT_SHOW_DATE)
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