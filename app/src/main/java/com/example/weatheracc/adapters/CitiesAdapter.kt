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
                //                when (city.status) {
//                    "Sunny" -> {
//                        itemContainer.setBackgroundResource(R.drawable.background_sunny)
//                    }
//                    "Clouds" -> {
//                        itemContainer.setBackgroundResource(R.drawable.background_clouds)
//                    }
//                    "Clear Sky" -> {
                        itemContainer.setBackgroundResource(R.drawable.background_clear_sky)
//                    }
//                }
                CityName.text = city.name
                Date.text = city.weather.firstOrNull()?.description
                Temperature.text = "${city.main.temp} °C"
                setOnClickListener { listener(city) }
            }
        }
    }


}