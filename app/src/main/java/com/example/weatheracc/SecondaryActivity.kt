package com.example.weatheracc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.weatheracc.R
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val message = intent.let {
            Log.d(
                "SecondaryActivity",
                "Parcelable ${it.getParcelableExtra<CityModel>(CITY_MODEL_KEY)}"
            )
            it.getStringExtra(MESSAGE_KEY)
        }
        Log.d("SecondaryActivity", "String extra $message")
        completeActivityButton.setOnClickListener {
            val intent = Intent().putExtra(MESSAGE_KEY, message)
            setResult(RESULT_OK, intent)
            finish()
        }

        secondaryActivityText.apply { text = message }
    }
}
