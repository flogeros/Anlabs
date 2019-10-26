package com.example.testmoxyfragment

import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IBriefWeatherView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayBriefWeather(smth : View, briefWeatherData: ArrayList<Items>?)
}