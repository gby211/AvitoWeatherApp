package com.pavel.avitoweatherapp.presentation.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pavel.avitoweatherapp.databinding.ItemForcastDayBinding
import com.pavel.avitoweatherapp.domain.model.WeatherCondition
import com.pavel.avitoweatherapp.presentation.utils.diffcallback.ForecastCallBack

class ForecastDayListAdapter :
    ListAdapter<WeatherCondition, ForecastDayListAdapter.ForecastViewHolder>(ForecastCallBack()) {

    inner class ForecastViewHolder(private val binding: ItemForcastDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(receivedItem: WeatherCondition) {
            binding.apply {
                textTemp.text = "${receivedItem.main.temp.toString()/*TODO*/}℃"

//                val date = LocalDateTime.parse(receivedItem.dt.toString(), DateTimeFormatter.ISO_DATE_TIME)
//                date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
                val time = receivedItem.dtTxt.split(" ")[1].dropLast(3)
                textTime.text = "${if (time[0] != '0') time[0] else ""}${time.drop(1)}"
//                    DateUtils.getRelativeTimeSpanString(receivedItem.dt.toLong())
//                textTime.text = receivedItem.dt.toString() //TODO

                textWind.text = "${receivedItem.wind.speed.toString()} м/с"
                textPressure.text = "${receivedItem.main.pressure.toString()} мм рт. ст."
                textHumidity.text = "${receivedItem.main.humidity.toString()} %"
                Glide.with(itemView.context)
                    .load("http://openweathermap.org/img/wn/${receivedItem.weather[0].icon}@2x.png")
                    .fitCenter()
                    .into(imageViewWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForcastDayBinding.inflate(
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