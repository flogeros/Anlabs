package com.example.lab_4

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.short_information.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ShortInformation : Fragment() {
    companion object {
        const val KEY = "WEATHER_KEY_"
        const val CITY_ID = 64
    }

    private var shortInformationData: ArrayList<Items>? = null
    private var detailedInformationData: ArrayList<Items>? = null

    private val onItemClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as RecyclerView.ViewHolder
        val position = viewHolder.adapterPosition
        val thisItem = shortInformationData!![position]

        val chosenData = detailedInformationData!!.filter { currWeather -> (currWeather.date == thisItem.date)} as ArrayList<Items>?

        when {
            resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT -> {
                val intent = Intent(context, WeatherActivity::class.java)
                for (i in 0 until chosenData!!.size) {
                    intent.putExtra(KEY + chosenData[i].timeOfDay, chosenData[i].toString())
                }
                startActivity(intent)
            }
            else -> {
                val fragmentTwo = DetailedInformation()
                val bundle = Bundle()

                for (i in 0 until chosenData!!.size) {
                    bundle.putString(KEY + chosenData[i].timeOfDay, chosenData[i].toString())
                }
                fragmentTwo.arguments = bundle

                fragmentManager!!.beginTransaction()
                    .replace(R.id.right_side, fragmentTwo)
                    .commit()
             }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val item : View = inflater.inflate(R.layout.short_information, container, false)

        item.isFocusableInTouchMode = true
        item.requestFocus()
        item.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true
                    }
                }
                return false
            }
        })

        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create<API>(API::class.java)
        val call = service.getWeatherList(CITY_ID)

        call.enqueue(object : Callback<List<Items>> {
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                val weatherData = (response.body() as? ArrayList<Items>)!!
                detailedInformationData = weatherData

                shortInformationData = weatherData.filter { currWeather -> (currWeather.timeOfDay == "2")} as ArrayList<Items>
                val weatherAdapter = ItemsAdapter(shortInformationData)
                item.weather_brief_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                item.weather_brief_list.adapter = weatherAdapter
                weatherAdapter.setItemClickListener(onItemClickListener)
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                val someData = arrayListOf<Items>()
                for (i in 0..3) {
                    someData.add(Items("fail", i.toString(), "fail", "fail", "fail", "fail", "fail"))
                }
                shortInformationData = someData
                detailedInformationData = someData
                Toast.makeText(activity!!.applicationContext, "Fail", Toast.LENGTH_LONG).show()
                val weatherAdapter = ItemsAdapter(shortInformationData)
                item.weather_brief_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                item.weather_brief_list.adapter = weatherAdapter
                weatherAdapter.setItemClickListener(onItemClickListener)
            }
        })

        return item
    }
}