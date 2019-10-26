package com.example.testmoxyfragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.short_information.view.*

class BriefWeatherFragment : MvpAppCompatFragment(), IBriefWeatherView {
    @InjectPresenter
    lateinit var mPresenter: BriefWeatherPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val smth: View = inflater.inflate(R.layout.short_information, container, false)
        with(ItemClickSupport.addTo(smth.weather_brief_list)) {
            setOnItemClickListener { _, position, _ -> mPresenter.handleItemClick(activity!!, position) }
        }

        mPresenter.loadWeather(activity!!, smth)

        return smth
    }

    override fun displayBriefWeather(smth : View, briefWeatherData: ArrayList<Items>?) {
        val weatherAdapter = ItemsAdapter(briefWeatherData)
        smth.weather_brief_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        smth.weather_brief_list.adapter = weatherAdapter
    }
}