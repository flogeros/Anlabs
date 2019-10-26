package com.example.testmoxyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.detailed_information.view.*

class DetailedWeatherFragment : MvpAppCompatFragment() {
    companion object {
        val DETAILED_WEATHER_KEY = "DETAILED_WEATHER_KEY_"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val smth : View = inflater.inflate(R.layout.detailed_information, container, false)

        val data0 = arguments!!.getString(DETAILED_WEATHER_KEY + 0.toString())
        val data1 = arguments!!.getString(DETAILED_WEATHER_KEY + 1.toString())
        val data2 = arguments!!.getString(DETAILED_WEATHER_KEY + 2.toString())
        val data3 = arguments!!.getString(DETAILED_WEATHER_KEY + 3.toString())

        smth.detailed_text_0.setText("Ночь\n" + data0)
        smth.detailed_text_1.setText("Утро\n" + data1)
        smth.detailed_text_2.setText("День\n" + data2)
        smth.detailed_text_3.setText("Вечер\n" + data3)

        return smth
    }
}