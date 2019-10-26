package com.example.testmoxyfragment

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity

// Sources
// https://github.com/Arty212/MoxyTest
// https://www.slideshare.net/YuriShmakov/mvp-moxy
// https://github.com/senneco/MoxyCases/tree/master/app/src/main/java/net/senneco/moxy/cases
// https://habr.com/ru/post/275255/#creating-model
// https://habr.com/ru/post/276189/
// https://habr.com/ru/company/redmadrobot/blog/325816/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentOne = BriefWeatherFragment()
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_portrait, fragmentOne)
                .addToBackStack(null)
                .commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_land_left, fragmentOne)
                .addToBackStack(null)
                .commit()
        }
    }
}
