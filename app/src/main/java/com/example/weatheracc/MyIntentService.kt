package com.example.weatheracc

import android.app.IntentService
import android.content.Intent
import android.util.Log

// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
const val ACTION_FOO = "com.example.weatheracc.action.FOO"


const val EXTRA_PARAM1 = "com.example.weatheracc.extra.PARAM1"


class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1) ?: "DEFAULT"
                handleMessage(param1)
            }
        }
    }

    private fun handleMessage(message: String) {
        Log.d("MyIntentService", "Message : $message")
    }

}
