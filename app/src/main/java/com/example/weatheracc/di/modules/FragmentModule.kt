package com.example.weatheracc.di.modules

import com.example.weatheracc.ui.fragment.ForecastListFragment
import com.example.weatheracc.ui.fragment.SavedCityFragment
import com.example.weatheracc.ui.fragment.SearchFragment
import com.example.weatheracc.ui.fragment.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    internal abstract fun bindSavedCityFragment(): SavedCityFragment

    @ContributesAndroidInjector
    internal abstract fun bindForecastListFragment(): ForecastListFragment

    @ContributesAndroidInjector
    internal abstract fun bindSplashFragment(): SplashFragment
}