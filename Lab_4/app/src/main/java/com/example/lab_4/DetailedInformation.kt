package com.example.lab_4

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detailed_information.view.*

class DetailedInformation : Fragment() {
    companion object {
        const val KEY = "WEATHER_KEY_"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val item : View = inflater.inflate(R.layout.detailed_information, container, false)

        item.detailed_text_0.text = getString(R.string.night) + "\n" + arguments!!.getString(KEY + 0.toString())
        item.detailed_text_1.text = getString(R.string.morning) + "\n" + arguments!!.getString(KEY + 1.toString())
        item.detailed_text_2.text = getString(R.string.day) + "\n" + arguments!!.getString(KEY + 2.toString())
        item.detailed_text_3.text = getString(R.string.evening) + "\n" + arguments!!.getString(KEY + 3.toString())

        return item
    }
}