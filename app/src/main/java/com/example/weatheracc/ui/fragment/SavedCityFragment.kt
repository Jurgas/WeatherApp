package com.example.weatheracc.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitiesAdapter
import com.example.weatheracc.models.Units
import com.example.weatheracc.viewModels.SavedCityViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.saved_city_fragment.view.*
import javax.inject.Inject

class SavedCityFragment : DaggerFragment() {

    private val citiesAdapter by lazy {
        CitiesAdapter {
            findNavController().navigate(
                SavedCityFragmentDirections.actionSavedCityFragmentToDetailsFragment(it)
            )
        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SavedCityViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.saved_city_fragment, container, false).apply {
            floatingActionButton.setColorFilter(Color.WHITE)
            textSwitcher.setOnClickListener {
                viewModel.updateUnits()
            }
            rvCity.adapter = citiesAdapter
            floatingActionButton.setOnClickListener {
                findNavController().navigate(
                    SavedCityFragmentDirections.actionSavedCityFragmentToSearchFragment()
                )
            }
            with(viewModel) {
                weatherList.observe(viewLifecycleOwner, Observer {
                    citiesAdapter.submitList(it)
                })

                units.observe(viewLifecycleOwner, Observer {
                    textSwitcher.setText(getText(if (it == Units.METRIC) R.string.units_metric else R.string.units_imperial))
                })
            }
        }
    }
}


