package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.HourlyWeather
import kotlinx.android.synthetic.main.item_one_hour_forecast.view.*

class DayWeatherAdapter(
    private val listener: (HourlyWeather) -> Unit
) : ListAdapter<HourlyWeather, DayWeatherAdapter.DayWeatherViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherViewHolder =
        DayWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_one_hour_forecast,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DayWeatherViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HourlyWeather>() {
            override fun areItemsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather) =
                oldItem.dt == newItem.dt


            override fun areContentsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather) =
                oldItem == newItem

        }
    }

    class DayWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hourlyWeather: HourlyWeather, listener: (HourlyWeather) -> Unit) {
            itemView.apply {
                tvHour.text = parseToHourMinutes(hourlyWeather.dt) // tutaj trzeba dodac timezone
                tvHourDescription.text = hourlyWeather.weather.firstOrNull()?.let {
                    it.description.capitalize()
                }
                tvHourTemp.text = "${hourlyWeather.main.temp.toInt()}°"
                setOnClickListener { listener(hourlyWeather) }
            }
        }
    }
}