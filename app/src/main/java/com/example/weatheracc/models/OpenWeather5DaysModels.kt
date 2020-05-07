package com.example.weatheracc.models

import com.google.gson.annotations.SerializedName

data class WeatherForecast5Days(
    @SerializedName("cod") val cod: Int,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<HourlyWeather>,
    @SerializedName("city") val city: City
)

data class HourlyWeather(
    @SerializedName("dt") val dt: Int,
    @SerializedName("main") val main: Main5Days,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("rain") val rain: Rain?,
    @SerializedName("snow") val snow: Snow?,
    @SerializedName("sys") val sys: Sys5Days,
    @SerializedName("dt_txt") val dt_txt: String
)

data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: Coord,
    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)

data class Main5Days(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val sea_level: Int,
    @SerializedName("grnd_level") val grnd_level: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_kf") val temp_kf: Double
)

data class Rain(
    @SerializedName("3h") val rain: Double
)

data class Snow(
    @SerializedName("3h") val snow: Double
)

data class Sys5Days(
    @SerializedName("pod") val pod: String
)
