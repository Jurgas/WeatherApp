package com.example.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.R
import com.example.weatheracc.viewModels.SplashViewModel
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SplashViewModel> { factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            delay(2500)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToForecastListFragment2())
        }

    }


}
