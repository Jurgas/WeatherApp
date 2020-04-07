package com.example.weatheracc.repository.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weatheracc.models.WeatherForecast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [WeatherForecast::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherForecastDao(): WeatherForecastDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "weather_acc.db"
            )
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        populateDB(context)
                    }

                    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                        super.onDestructiveMigration(db)
                        populateDB(context)
                    }
                })
                .build()
        }

        private fun populateDB(context: Context) {
            val list = listOf(
                WeatherForecast(
                    "1",
                    "Warszawa",
                    status = "Clear Sky",
                    temperature = 15
                ),
                WeatherForecast(
                    "2",
                    "Wrocław",
                    status = "Clouds",
                    temperature = 12
                ),
                WeatherForecast(
                    "3",
                    "Warszawa",
                    status = "Sunny",
                    temperature = 15
                )
            )
            GlobalScope.launch {
                buildDatabase(context).weatherForecastDao().insert(list)
            }
        }
    }
}