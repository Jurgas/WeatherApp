package com.example.weatheracc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "weather_forecast")
data class WeatherForecast(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: String = "31.03.2020",
    @SerializedName("status") val status: String,
    @SerializedName("temperature") val temperature: Int
)
