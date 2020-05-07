package com.example.weatheracc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatheracc.R
import kotlinx.android.synthetic.main.item_forecast_description.view.*

class DescriptionAdapter(
    private val listener: (Pair<String, String>) -> Unit
) : ListAdapter<Pair<String, String>, DescriptionAdapter.DescriptionViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder =
        DescriptionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_forecast_description,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pair<String, String>>() {
            override fun areItemsTheSame(
                oldItem: Pair<String, String>,
                newItem: Pair<String, String>
            ) =
                oldItem.first == newItem.first


            override fun areContentsTheSame(
                oldItem: Pair<String, String>,
                newItem: Pair<String, String>
            ) =
                oldItem == newItem

        }
    }

    class DescriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(description: Pair<String, String>, listener: (Pair<String, String>) -> Unit) {
            itemView.apply {
                tvForecastDescription.text = description.first
                tvForecastDescriptionValue.text = description.second
            }
        }
    }
}