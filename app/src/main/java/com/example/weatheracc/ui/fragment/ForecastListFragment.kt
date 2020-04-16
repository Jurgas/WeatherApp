package com.example.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.R
import com.example.weatheracc.viewModels.ForecastListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*
import javax.inject.Inject

class ForecastListFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<ForecastListViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_list_fragment, container, false).apply {
            addWeather.setOnClickListener {
                findNavController().navigate(
                    ForecastListFragmentDirections.actionForecastListFragmentToSearchFragment2()
                )
            }
        }

    }
}
