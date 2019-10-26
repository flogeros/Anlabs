package com.example.testmoxyfragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailedWeatherActivity : AppCompatActivity() {

    companion object {
        val DETAILED_WEATHER_KEY = "DETAILED_WEATHER_KEY_"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val fragment = DetailedWeatherFragment()
        val bundle = Bundle()

        bundle.putString(DETAILED_WEATHER_KEY + 0.toString(), intent.getStringExtra(DETAILED_WEATHER_KEY + 0.toString()))
        bundle.putString(DETAILED_WEATHER_KEY + 1.toString(), intent.getStringExtra(DETAILED_WEATHER_KEY + 1.toString()))
        bundle.putString(DETAILED_WEATHER_KEY + 2.toString(), intent.getStringExtra(DETAILED_WEATHER_KEY + 2.toString()))
        bundle.putString(DETAILED_WEATHER_KEY + 3.toString(), intent.getStringExtra(DETAILED_WEATHER_KEY + 3.toString()))

        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_detailed_activity, fragment)
            .addToBackStack(null)
            .commit()
    }
}
