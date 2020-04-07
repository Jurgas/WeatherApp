package com.example.weatheracc.repository

import com.example.weatheracc.repository.local.WeatherForecastDao
import com.example.weatheracc.repository.remote.OpenWeatherService

class Repository(
    private val openWeatherService: OpenWeatherService,
    private val weatherForecastDao: WeatherForecastDao
) {

    fun getWeatherList() = weatherForecastDao.getAll()


}
