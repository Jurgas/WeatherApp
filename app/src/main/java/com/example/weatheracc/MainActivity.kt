package com.example.weatheracc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatheracc.ui.fragment.ForecastListFragment
import com.example.weatheracc.ui.fragment.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
