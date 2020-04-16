package com.example.weatheracc.di.modules

import androidx.lifecycle.ViewModel
import com.example.weatheracc.viewModels.ForecastListViewModel
import com.example.weatheracc.viewModels.SavedCityViewModel
import com.example.weatheracc.viewModels.SearchViewModel
import com.example.weatheracc.viewModels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(key = SavedCityViewModel::class)
    abstract fun bindSavedCityViewModel(viewModel: SavedCityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(key = ForecastListViewModel::class)
    abstract fun bindForecastListViewModel(viewModel: ForecastListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(key = SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(key = SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}