package com.example.weatheracc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("MyReceiver", "Work")
        Toast.makeText(context, "Flight Mode", Toast.LENGTH_LONG).show()
    }
}
