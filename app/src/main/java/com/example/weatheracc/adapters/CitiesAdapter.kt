package com.example.weatheracc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.Units
import com.example.weatheracc.models.WeatherForecast
import com.example.weatheracc.repository.local.getUnits
import kotlinx.android.synthetic.main.item_saved_city.view.*

class CitiesAdapter(
    private val listener: (WeatherForecast) -> Unit
) : ListAdapter<WeatherForecast, CitiesAdapter.CitiesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        CitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_saved_city,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherForecast>() {
            override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast) =
                oldItem == newItem

        }
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: WeatherForecast, listener: (WeatherForecast) -> Unit) {
            itemView.apply {
                val sharedPref = context.getSharedPreferences("weather_app", Context.MODE_PRIVATE)
                city.weather.firstOrNull()?.let {
                    when (it.icon) {
                        "01d" -> {
                            if ((city.main.temp >= 25 && sharedPref.getUnits() == Units.METRIC) || city.main.temp >= 77) {
                                ivIcon.setImageResource(R.drawable.background_orange_sun)
                                itemContainer.setBackgroundResource(R.drawable.background_sunny_rectangle)
                            } else {
                                ivIcon.setImageResource(R.drawable.ic_sun)
                                itemContainer.setBackgroundResource(R.drawable.background_clear_sky_rectangle)
                            }
                        }
                        "02d" -> {
                            ivIcon.setImageResource(R.drawable.ic_cloud_sun)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                        "03d", "04d" -> {
                            ivIcon.setImageResource(R.drawable.ic_cloud)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                        "09d", "10d" -> {
                            ivIcon.setImageResource(R.drawable.ic_cloud_rain)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                        "11d" -> {
                            ivIcon.setImageResource(R.drawable.ic_thunder)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                        "13d" -> {
                            ivIcon.setImageResource(R.drawable.ic_snow)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                        "50d" -> {
                            ivIcon.setImageResource(R.drawable.ic_mist)
                            itemContainer.setBackgroundResource(R.drawable.background_mist_rectangle)
                        }
                        "01n" -> {
                            itemContainer.setBackgroundResource(R.drawable.background_night_rectangle)
                        }
                        "02n", "03n", "04n", "09n", "10n", "11n", "13n", "50n" -> {
                            itemContainer.setBackgroundResource(R.drawable.background_night_rectangle)
                            ivIcon.setImageResource(R.drawable.ic_cloud)
                        }
                        else -> {
                            ivIcon.setImageResource(R.drawable.ic_cloud)
                            itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                        }
                    }
                }
                CityName.text = city.name
                Date.text = parseToDayMonthYear(city.dt + city.sys.timezone)
                Temperature.text = "${city.main.temp_max.toInt()}° / ${city.main.temp_min.toInt()}°"
                setOnClickListener { listener(city) }
            }
        }
    }


}