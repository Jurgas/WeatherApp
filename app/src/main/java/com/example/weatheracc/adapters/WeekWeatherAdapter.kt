package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.HourlyWeather
import kotlinx.android.synthetic.main.item_one_day_forecast.view.*

class WeekWeatherAdapter(
    private val listener: (HourlyWeather) -> Unit
) : ListAdapter<HourlyWeather, WeekWeatherAdapter.WeekWeatherViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekWeatherViewHolder =
        WeekWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_one_day_forecast,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: WeekWeatherViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HourlyWeather>() {
            override fun areItemsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather) =
                oldItem.dt == newItem.dt


            override fun areContentsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather) =
                oldItem == newItem

        }
    }

    class WeekWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hourlyWeather: HourlyWeather, listener: (HourlyWeather) -> Unit) {
            itemView.apply {
                tvDayName.text = parseToDayName(hourlyWeather.dt)
                hourlyWeather.weather.firstOrNull()?.let {
                    when (it.icon) {
                        "01d", "01n" -> ivDayIcon.setImageResource(R.drawable.ic_sun)
                        "02d", "02n" -> ivDayIcon.setImageResource(R.drawable.ic_cloud_sun)
                        "03d", "03n", "04d", "04n" -> ivDayIcon.setImageResource(R.drawable.ic_cloud)
                        "09d", "10d" -> ivDayIcon.setImageResource(R.drawable.ic_cloud_rain)
                        "11d" -> ivDayIcon.setImageResource(R.drawable.ic_thunder)
                        "13d" -> ivDayIcon.setImageResource(R.drawable.ic_snow)
                        "50d" -> ivDayIcon.setImageResource(R.drawable.ic_mist)
                        else -> ivDayIcon.setImageResource(R.drawable.ic_cloud)
                    }
                }
                tvDayTemp.text =
                    "${hourlyWeather.main.temp_max.toInt()}°/${hourlyWeather.main.temp_min.toInt()}°"
                setBackgroundResource(R.drawable.rounded_background_white)
                setOnClickListener {
                    listener(hourlyWeather)
                }
            }
        }
    }


}