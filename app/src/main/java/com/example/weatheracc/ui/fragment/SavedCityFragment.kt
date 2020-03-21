package com.example.weatheracc.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitiesAdapter
import com.example.weatheracc.models.CityWeatherModel
import com.example.weatheracc.viewModels.SavedCityViewModel
import kotlinx.android.synthetic.main.saved_city_fragment.view.*

class SavedCityFragment : Fragment() {

    private val citiesAdapter by lazy {
        CitiesAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
        }
    }

    private val citiesList = mutableListOf(
        CityWeatherModel(
            "1",
            "Warszawa",
            status = "Clear Sky",
            temperature = 15
        ),
        CityWeatherModel(
            "2",
            "Wrocław",
            status = "Clouds",
            temperature = 12
        ),
        CityWeatherModel(
            "3",
            "Poznań",
            status = "Clear Sky",
            temperature = 10
        ),
        CityWeatherModel(
            "4",
            "Gdańsk",
            status = "Clouds",
            temperature = 20
        ),
        CityWeatherModel(
            "5",
            "Los Angeles",
            status = "Sunny",
            temperature = 22
        ),
        CityWeatherModel(
            "6",
            "Miami",
            status = "Sunny",
            temperature = 30
        )
    )

    private lateinit var viewModel: SavedCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.saved_city_fragment, container, false)
        rootView.rvCity.adapter = citiesAdapter.apply {
            submitList(citiesList)
        }
        rootView.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                SavedCityFragmentDirections.actionSavedCityFragmentToCitySearchFragment()
            )
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedCityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
