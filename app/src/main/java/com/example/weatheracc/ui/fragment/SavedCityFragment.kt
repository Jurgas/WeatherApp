package com.example.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.R
import com.example.weatheracc.adapters.CitiesAdapter
import com.example.weatheracc.viewModels.SavedCityViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.saved_city_fragment.view.*
import javax.inject.Inject

class SavedCityFragment : DaggerFragment() {

    private val citiesAdapter by lazy {
        CitiesAdapter {
            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
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
            viewModel.weatherList.observe(viewLifecycleOwner, Observer {
                citiesAdapter.submitList(it)
            })
            rvCity.adapter = citiesAdapter
            floatingActionButton.setOnClickListener {
                findNavController().navigate(
                    SavedCityFragmentDirections.actionSavedCityFragmentToCitySearchFragment()
                )
            }
        }
    }
}


