package com.example.testmoxyfragment

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class Items {
    var date: String? = null
    @SerializedName("tod") var timeOfDay: String? = null
    var pressure: String? = null
    var temp: String? = null
    var humidity: String? = null
    var wind: String? = null
    var cloud: String? = null
    var creationDate: String? = null
    var id : Int = 0

    fun setCreationDate() {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currDate = formatter.format(Calendar.getInstance().getTime())
        creationDate = currDate.toString()
    }

    constructor(d : String, tod : String, p : String, t : String, h : String, w : String, c : String, cd : String, i : Int) {
        date = d
        timeOfDay = tod
        temp = t
        humidity = h
        wind = w
        cloud = c
        creationDate = cd
        id = i
    }

    constructor(d : String, tod : String, p : String, t : String, h : String, w : String, c : String) {
        date = d
        timeOfDay = tod
        temp = t
        humidity = h
        wind = w
        cloud = c
    }

    override fun toString(): String {
        return StringBuilder()
//            .append("Дата : ")
            .append(date)
            .append("\n")
//            .append("timeOfDay : ")
//            .append(timeOfDay)
//            .append("\n")
//            .append("Давление : ")
            .append(pressure)
            .append("\n")
//            .append("Температура : ")
            .append(temp)
            .append("\n")
//            .append("Влажность : ")
            .append(humidity)
            .append("\n")
//            .append("Ветер : ")
            .append(wind)
            .append("\n")
//            .append(creationDate)
//            .append("\n")
//            .append("cloud : ")
            .append(cloud)
            .toString()
    }
}