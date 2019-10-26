package com.example.testmoxyfragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

@InjectViewState
class BriefWeatherPresenter : MvpPresenter<IBriefWeatherView>() {
    companion object {
        const val DETAILED_WEATHER_KEY = "DETAILED_WEATHER_KEY_"
        const val CITY_ID = 22
    }

    private var briefWeatherData: ArrayList<Items>? = null
    private var detailedWeatherData: ArrayList<Items>? = null

    @SuppressLint("SimpleDateFormat")
    fun loadWeather(activity: Activity, view : View) {
        val dbhelper = DBHelper.getInstance(activity.applicationContext!!)
        val dbdata = dbhelper.getAllWeather()
        var diffInSec = 0
        if (dbdata.size > 0) {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val currDate = formatter.parse(formatter.format(Calendar.getInstance().getTime()).toString())
            val lastDate = formatter.parse(dbdata.last().creationDate)
            diffInSec = TimeUnit.MILLISECONDS.toSeconds(currDate.getTime() - lastDate.getTime()).toInt()
        }

        if (dbdata.size == 0 || diffInSec > 2 * 3600) {
            dbhelper.clearTable(SQLHelper.WEATHER_TABLE)
            Toast.makeText(activity.applicationContext, "VITASKIVAIU POGODY RETROFITOM", Toast.LENGTH_LONG).show()

            // rofli s retrofitom
            val retrofit = Retrofit.Builder()
                .baseUrl("http://icomms.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<API>(API::class.java)
            val call = service.getWeatherList(CITY_ID)

            call.enqueue(object : Callback<List<Items>> {
                override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                    val weatherData = (response.body() as? ArrayList<Items>)!!
                    for (i in 0 until weatherData.size) {
                        weatherData[i].setCreationDate()
                    }

                    detailedWeatherData = weatherData
                    dbhelper.addAllWeather(weatherData)
                    briefWeatherData = detailedWeatherData!!.filter { currWeather -> (currWeather.timeOfDay == "2") } as ArrayList<Items>
                    viewState.displayBriefWeather(view, briefWeatherData)
                }

                override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                    Toast.makeText(activity.applicationContext, "PROIZOSHOL DDOS", Toast.LENGTH_LONG).show()
                }
            })
            //
        }
        else {
            Toast.makeText(activity.applicationContext, "VITASKIVAIU POGODY IZ BAZI", Toast.LENGTH_LONG).show()
            detailedWeatherData = dbhelper.getAllWeather()
            briefWeatherData = detailedWeatherData!!.filter { currWeather -> (currWeather.timeOfDay == "2") } as ArrayList<Items>
            viewState.displayBriefWeather(view, briefWeatherData)
        }
    }

    fun handleItemClick(activity: Activity, position: Int) {
        Toast.makeText(activity.applicationContext, "You Clicked: " + briefWeatherData!![position].temp, Toast.LENGTH_SHORT).show()
        val chosenData = detailedWeatherData!!.filter { currWeather -> (currWeather.date == briefWeatherData!![position].date) } as ArrayList<Items>?

        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(activity.applicationContext, DetailedWeatherActivity::class.java)
            for (i in 0 until chosenData!!.size) {
                intent.putExtra(DETAILED_WEATHER_KEY + chosenData[i].timeOfDay, chosenData[i].toString())
            }
            activity.startActivity(intent)
        } else {
            val fragmentTwo = DetailedWeatherFragment()
            val bundle = Bundle()

            //put your ArrayList data in bundle
            for (i in 0 until chosenData!!.size) {
                bundle.putString(DETAILED_WEATHER_KEY + chosenData[i].timeOfDay, chosenData[i].toString())
            }
            fragmentTwo.arguments = bundle
            (activity as AppCompatActivity).supportFragmentManager!!.beginTransaction()
                .replace(R.id.frame_land_right, fragmentTwo)
                .addToBackStack(null)
                .commit()
        }
    }
}