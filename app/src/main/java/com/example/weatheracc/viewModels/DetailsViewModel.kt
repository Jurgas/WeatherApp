package com.example.weatheracc.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatheracc.adapters.parseToDayName
import com.example.weatheracc.adapters.parseToHourMinutes
import com.example.weatheracc.models.HourlyWeather
import com.example.weatheracc.models.WeatherForecast5Days
import com.example.weatheracc.repository.Repository
import com.example.weatheracc.repository.local.getUnits
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val weather5Days = MutableLiveData<WeatherForecast5Days>()
    val weekForecast5DaysList = MutableLiveData<List<HourlyWeather>>()
    val dayForecast5DaysList = MutableLiveData<List<HourlyWeather>>()
    val descriptionForecast5DaysList = MutableLiveData<List<Pair<String, String>>>()

    fun fetchCityWeather(id: Long) {
        viewModelScope.launch {
            weather5Days.postValue(
                repository.fetchWeather5DaysByCityId(
                    id,
                    sharedPreferences.getUnits()
                )
            )
        }
    }

    fun updateWeekForecast5DaysList(weatherForecast5Days: WeatherForecast5Days) {
        val tz = weatherForecast5Days.city.timezone
        val list = mutableListOf<HourlyWeather>()
        weatherForecast5Days.list.let {
            var actualDay = parseToDayName(it[0].dt + tz)
            var maxTemp = it[0].main.temp_max
            var minTemp = it[0].main.temp_min
            for (i in it.indices) {
                val day = parseToDayName(it[i].dt + tz)
                if (day == actualDay) {
                    if (maxTemp < it[i].main.temp_max)
                        maxTemp = it[i].main.temp_max
                    if (minTemp > it[i].main.temp_min)
                        minTemp = it[i].main.temp_min
                } else {
                    val main = it[i - 1].main.copy(temp_min = minTemp, temp_max = maxTemp)
                    actualDay = parseToDayName(it[i].dt + tz)
                    list.add(it[i - 1].copy(main = main, dt = it[i - 1].dt + tz))
                    maxTemp = it[i - 1].main.temp_max
                    minTemp = it[i - 1].main.temp_min
                }
            }
            val main = it[it.lastIndex].main.copy(temp_min = minTemp, temp_max = maxTemp)
            list.add(it[it.lastIndex].copy(main = main, dt = it[it.lastIndex].dt + tz))
        }
        weekForecast5DaysList.postValue(list)
    }

    fun updateDayForecast5DaysList(dayName: String) {
        val tz = weather5Days.value?.city?.timezone ?: 0
        dayForecast5DaysList.postValue(weather5Days.value?.list?.let { l ->
            l.map { it.copy(dt = it.dt + tz) }
                .filter { parseToDayName(it.dt) == dayName }
        })
    }


    fun updateDescriptionForecast5DaysList() {
        val list = mutableListOf<Pair<String, String>>()
        list.addAll(addSunriseSunset())
        descriptionForecast5DaysList.postValue(list)
    }

    private fun addSunriseSunset() =
        mutableListOf<Pair<String, String>>().apply {
            val tz = weather5Days.value?.city?.timezone ?: 0
            this.add(
                Pair(
                    "Sunrise",
                    parseToHourMinutes((weather5Days.value?.city?.sunrise ?: 0) + tz)
                )
            )
            this.add(
                Pair(
                    "Sunset",
                    parseToHourMinutes((weather5Days.value?.city?.sunset ?: 0) + tz)
                )
            )
        }

    fun updateDescriptionHourForecast5DaysList(hourlyWeather: HourlyWeather) {
        val list = mutableListOf<Pair<String, String>>()
        list.add(Pair("Humidity", "${hourlyWeather.main.humidity}%"))
        list.add(Pair("Wind", "${hourlyWeather.wind.speed.toInt()}m/s"))
        list.add(Pair("Feels like", "${hourlyWeather.main.feels_like.toInt()}°"))
        list.add(Pair("Pressure", "${hourlyWeather.main.pressure}hPa"))
        list.addAll(addSunriseSunset())
        descriptionForecast5DaysList.postValue(list)
    }
}
