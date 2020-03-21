package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import com.example.weatheracc.models.CitySearchModel
import kotlinx.android.synthetic.main.item_search_city.view.*

class CitySearchAdapter(
    private val listener: (CitySearchModel) -> Unit
) : ListAdapter<CitySearchModel, CitySearchAdapter.CitySearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitySearchViewHolder =
        CitySearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_city,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CitySearchModel>() {
            override fun areItemsTheSame(oldItem: CitySearchModel, newItem: CitySearchModel) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: CitySearchModel, newItem: CitySearchModel) =
                oldItem == newItem

        }
    }

    class CitySearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: CitySearchModel, listener: (CitySearchModel) -> Unit) {
            itemView.apply {
                tvCityNameSearch.text = city.name
                tvStateSearch.text = " , ${city.state}"
                setOnClickListener { listener(city) }
            }
        }
    }


}