package com.example.weatheracc.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatheracc.models.WeatherForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherForecastDao: WeatherForecast): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherForecastDao: List<WeatherForecast>): List<Long>

    @Update
    suspend fun update(weatherForecastDao: WeatherForecast): Int

    @Delete
    suspend fun delete(weatherForecastDao: WeatherForecast): Int

    @Query("SELECT * FROM weather_forecast")
    fun getAll(): Flow<List<WeatherForecast>>
}