package com.example.weatheracc.ui.fragment

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BlendMode
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.weatheracc.*
import com.example.weatheracc.viewModels.ForecastListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.forecast_list_fragment.*
import kotlinx.android.synthetic.main.forecast_list_fragment.view.*

class ForecastListFragment : Fragment() {

    private lateinit var viewModel: ForecastListViewModel
    private lateinit var br: BroadcastReceiver
    private lateinit var filter: IntentFilter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.forecast_list_fragment, container, false)
        rootView.addWeather.setOnClickListener {
            findNavController().navigate(
                ForecastListFragmentDirections.actionForecastListFragmentToLocationListFragment()
            )
        }

        rootView.startServiceButton.setOnClickListener {
            val serviceIntent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, messageEditText.text.toString())
            }
            context!!.startService(serviceIntent)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        )

        br = MyReceiver()
        filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForecastListViewModel::class.java)
        // TODO: Use the ViewModel
        createActivityButton.setOnClickListener {
            val intent = Intent(context, SecondaryActivity::class.java).apply {
                putExtra(MESSAGE_KEY, "Welcome in Secondary Activity")
                putExtra(CITY_MODEL_KEY, CityModel("0", "Warsaw"))
            }
            startActivityForResult(intent, SECONDARY_RESULT_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SECONDARY_RESULT_CODE) {
            Log.d(
                "ForecastListFragment",
                "onActivityResult result code $resultCode, RESULT_CODE == -1"
            )
            Log.d(
                "ForecastListFragment",
                "onActivityResult data ${data?.getStringExtra(MESSAGE_KEY)}"
            )
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onResume() {
        super.onResume()
        context?.registerReceiver(br, filter)
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(br)
    }

}
