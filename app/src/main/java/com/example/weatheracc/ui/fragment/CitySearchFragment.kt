package com.example.weatheracc.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitySearchAdapter
import com.example.weatheracc.models.CitySearchModel
import com.example.weatheracc.viewModels.CitySearchViewModel
import kotlinx.android.synthetic.main.city_search_fragment.view.*

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

    private lateinit var viewModel: CitySearchViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CitySearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
