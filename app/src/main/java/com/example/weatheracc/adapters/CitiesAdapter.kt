package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.CityWeatherModel
import kotlinx.android.synthetic.main.item_saved_city.view.*

class CitiesAdapter(
    private val listener: (CityWeatherModel) -> Unit
) : ListAdapter<CityWeatherModel, CitiesAdapter.CitiesViewHolder>(DIFF_CALLBACK) {

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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CityWeatherModel>() {
            override fun areItemsTheSame(oldItem: CityWeatherModel, newItem: CityWeatherModel) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: CityWeatherModel, newItem: CityWeatherModel) =
                oldItem == newItem

        }
    }

    class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: CityWeatherModel, listener: (CityWeatherModel) -> Unit) {
            itemView.apply {
                when (city.status) {
                    "Sunny" -> {
                        itemContainer.setBackgroundResource(R.drawable.background_sunny)
                    }
                    "Clouds" -> {
                        itemContainer.setBackgroundResource(R.drawable.background_clouds)
                    }
                    "Clear Sky" -> {
                        itemContainer.setBackgroundResource(R.drawable.background_clear_sky)
                    }
                }
                CityName.text = city.name
                Date.text = city.date
                Temperature.text = "${city.temperature} °C"
                setOnClickListener { listener(city) }
            }
        }
    }


}