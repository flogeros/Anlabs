package com.example.lab_4

import com.google.gson.annotations.SerializedName

class Items(d: String, tod: String, p: String, t: String, h: String, w: String, c: String) {
    var date: String? = d
    @SerializedName("tod") var timeOfDay: String? = tod
    var pressure: String? = p
    var temp: String? = t
    var humidity: String? = h
    var wind: String? = w
    var cloud: String? = c

    override fun toString(): String {
        return StringBuilder()
            .append("Дата: ")
            .append(date)
            .append("\n")
            .append("Давление: ")
            .append(pressure)
            .append("\n")
            .append("Температура: ")
            .append(temp)
            .append("\n")
            .append("Влажность: ")
            .append(humidity)
            .append("\n")
            .append("Ветер: ")
            .append(wind)
            .append("\n")
            .append("Осадки: ")
            .append(cloud)
            .toString()
    }
}