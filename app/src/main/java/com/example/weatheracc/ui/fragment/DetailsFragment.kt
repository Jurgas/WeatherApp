package com.example.weatheracc.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatheracc.R
import com.example.weatheracc.adapters.*
import com.example.weatheracc.viewModels.DetailsViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.details_fragment.view.*
import java.util.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment() {


    private val weekWeatherAdapter by lazy {
        WeekWeatherAdapter {
            viewModel.updateDayForecast5DaysList(parseToDayName(it.dt))
            viewModel.updateDescriptionForecast5DaysList()
        }
    }

    private val dayWeatherAdapter by lazy {
        DayWeatherAdapter {
            viewModel.updateDescriptionHourForecast5DaysList(it)
        }
    }

    private val descriptionAdapter by lazy {
        DescriptionAdapter {}
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailsViewModel> { factory }
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false).apply {
            ibtnBack.setOnClickListener { findNavController().popBackStack() }
            rvWeekForecast.adapter = weekWeatherAdapter
            rvWeekForecast.addItemDecoration(MarginItemDecoration(25))
            rvHourForecast.adapter = dayWeatherAdapter
            rvDescriptionForecast.adapter = descriptionAdapter
            tvCity.text = "${args.weatherForecast.name}, ${Locale(
                "en",
                args.weatherForecast.sys.country
            ).displayCountry}"
            tvTemp.text = "${args.weatherForecast.main.temp_max.toInt()}°"

            with(viewModel) {
                fetchCityWeather(args.weatherForecast.id)
                weather5Days.observe(viewLifecycleOwner, Observer {
                    val time = Date().time / 1000 + it.city.timezone
                    tvDate.text = parseToDayMonthHourMinutes(time.toInt())
                    if (time in (it.city.sunrise + it.city.timezone + 1) until it.city.sunset + it.city.timezone)
                        setDayBackground(rootView)
                    else
                        setNightBackground(rootView)

                    it.list.firstOrNull()?.let { HourlyWeather ->
                        updateDayForecast5DaysList(parseToDayName(HourlyWeather.dt + it.city.timezone))
                    }
                    updateWeekForecast5DaysList(it)
                    updateDescriptionForecast5DaysList()
                })



                weekForecast5DaysList.observe(viewLifecycleOwner, Observer {
                    weekWeatherAdapter.submitList(it)
                })
                dayForecast5DaysList.observe(viewLifecycleOwner, Observer {
                    dayWeatherAdapter.submitList(it)
                })
                descriptionForecast5DaysList.observe(viewLifecycleOwner, Observer {
                    descriptionAdapter.submitList(it)
                })
            }
        }
    }


    private fun setDayBackground(view: View) {
        args.weatherForecast.weather.firstOrNull()?.let {
            when (it.main) {
                "Clear" -> {
                    if (args.weatherForecast.main.temp_max.toInt() >= 25) {
                        view.ivBackground.setImageResource(R.drawable.background_sun_hot)
                        view.ivSunOrange.visibility = View.VISIBLE
                    } else {
                        view.ivBackground.setImageResource(R.drawable.background_clear_sky)
                        view.ivSun.visibility = View.VISIBLE
                    }
                }
                "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Ash", "Squall", "Tornado" -> {
                    view.ivBackground.setImageResource(R.drawable.background_mist)
                    view.ivCloudsFog.visibility = View.VISIBLE
                }
                "Rain" -> {
                    view.ivBackground.setImageResource(R.drawable.background_rain)
                    view.ivRain.visibility = View.VISIBLE
                    view.ivCloudsRain.visibility = View.VISIBLE
                }
                "Thunderstorm" -> {
                    view.ivBackground.setImageResource(R.drawable.background_rain)
                    view.ivThunder.visibility = View.VISIBLE
                    view.ivCloudsThunderstorm.visibility = View.VISIBLE
                }
                "Snow" -> {
                    view.ivBackground.setImageResource(R.drawable.background_snow)
                    view.ivCloudsSnow.visibility = View.VISIBLE
                    view.ivSnow.visibility = View.VISIBLE
                }
                else -> {
                    view.ivBackground.setImageResource(R.drawable.background_clouds)
                    view.ivSun.visibility = View.VISIBLE
                    view.ivCloudsSun.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun setNightBackground(view: View) {
        args.weatherForecast.weather.firstOrNull()?.let {
            view.ivBackground.setImageResource(R.drawable.background_night)
            view.ivStars.visibility = View.VISIBLE
            when (it.main) {
                "Clear" -> {
                }
                else -> {
                    view.ivCloudsNight.visibility = View.VISIBLE
                }
            }
        }

    }


}
