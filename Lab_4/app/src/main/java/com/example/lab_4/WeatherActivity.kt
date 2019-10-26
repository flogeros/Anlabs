package com.example.lab_4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent

class WeatherActivity : AppCompatActivity() {
    companion object {
        const val KEY = "WEATHER_KEY_"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val fragment = DetailedInformation()
        val bundle = Bundle()

        bundle.putString(KEY + 0.toString(), intent.getStringExtra(KEY + 0.toString()))
        bundle.putString(KEY + 1.toString(), intent.getStringExtra(KEY + 1.toString()))
        bundle.putString(KEY + 2.toString(), intent.getStringExtra(KEY + 2.toString()))
        bundle.putString(KEY + 3.toString(), intent.getStringExtra(KEY + 3.toString()))

        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_detailed_activity, fragment)
            .commit()
    }
}
