package com.example.lab_4

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ShortInformation()
        when {
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_portrait, fragment)
                    .commit()
            }
            else -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.left_side, fragment)
                    .commit()
            }
        }
    }
}
