package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.WeatherForecast
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
                val time = java.util.Date().time / 1000 + city.sys.timezone
                city.weather.firstOrNull()?.let {
                    if (time !in (city.sys.sunrise + city.sys.timezone + 1) until city.sys.sunset + city.sys.timezone) {
                        itemContainer.setBackgroundResource(R.drawable.background_night_rectangle)
                    } else {
                        itemContainer.setBackgroundResource(R.drawable.background_night_rectangle)
                        when (it.main) {
                            "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Ash", "Squall", "Tornado" -> {
                                itemContainer.setBackgroundResource(R.drawable.background_mist_rectangle)
                                itemContainer.ivIcon.setImageResource(R.drawable.ic_mist)
                            }
                            "Clear" -> {
                                if (city.main.temp_max.toInt() >= 25) {
                                    itemContainer.setBackgroundResource(R.drawable.background_sunny_rectangle)
                                    itemContainer.ivIcon.setImageResource(R.drawable.ic_orange_sun)
                                } else {
                                    itemContainer.setBackgroundResource(R.drawable.background_clear_sky_rectangle)
                                    itemContainer.ivIcon.setImageResource(R.drawable.ic_sun)
                                }
                            }
                            else -> {
                                itemContainer.setBackgroundResource(R.drawable.background_clouds_rectangle)
                                itemContainer.ivIcon.setImageResource(R.drawable.ic_cloud)
                            }
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