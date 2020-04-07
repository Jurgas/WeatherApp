package com.example.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitySearchAdapter
import com.example.weatheracc.models.CitySearchModel
import com.example.weatheracc.viewModels.CitySearchViewModel
import kotlinx.android.synthetic.main.city_search_fragment.view.*
import javax.inject.Inject

class CitySearchFragment : Fragment() {

    private val citySearchAdapter by lazy {
        CitySearchAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
        }
    }

    private val citiesList = mutableListOf(
        CitySearchModel(
            "1",
            "Warszawa",
            "Poland"
        ),
        CitySearchModel(
            "2",
            "Wrocław",
            "Poland"
        ),
        CitySearchModel(
            "3",
            "Poznań",
            "Poland"
        ),
        CitySearchModel(
            "4",
            "Gdańsk",
            "Poland"
        ),
        CitySearchModel(
            "5",
            "Los Angeles",
            "United States"
        ),
        CitySearchModel(
            "6",
            "Miami",
            "United States"
        ),
        CitySearchModel(
            "7",
            "Warszawa",
            "Poland"
        ),
        CitySearchModel(
            "8",
            "Wrocław",
            "Poland"
        ),
        CitySearchModel(
            "9",
            "Poznań",
            "Poland"
        ),
        CitySearchModel(
            "10",
            "Gdańsk",
            "Poland"
        ),
        CitySearchModel(
            "11",
            "Los Angeles",
            "United States"
        ),
        CitySearchModel(
            "12",
            "Miami",
            "United States"
        ),
        CitySearchModel
            (
            "13",
            "Warszawa",
            "Poland"
        ),
        CitySearchModel(
            "14",
            "Wrocław",
            "Poland"
        ),
        CitySearchModel(
            "15",
            "Poznań",
            "Poland"
        ),
        CitySearchModel(
            "16",
            "Gdańsk",
            "Poland"
        ),
        CitySearchModel(
            "17",
            "Los Angeles",
            "United States"
        ),
        CitySearchModel(
            "18",
            "Miami",
            "United States"
        )
    )

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<CitySearchViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.city_search_fragment, container, false)
        rootView.rvCitySearch.adapter = citySearchAdapter.apply {
            submitList(citiesList)
        }
        return rootView
    }

}
