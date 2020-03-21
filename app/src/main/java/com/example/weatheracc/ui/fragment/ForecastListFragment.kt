package com.example.weatheracc.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitiesAdapter
import com.example.weatheracc.models.CityWeatherModel
import com.example.weatheracc.viewModels.ForecastListViewModel
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*
import kotlinx.android.synthetic.main.item_saved_city.view.*

class ForecastListFragment : Fragment() {

    private lateinit var viewModel: ForecastListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.forecast_list_fragment, container, false)
        rootView.addWeather.setOnClickListener {
            findNavController().navigate(
                ForecastListFragmentDirections.actionForecastListFragmentToSavedCityFragment()
            )
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        )
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForecastListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
