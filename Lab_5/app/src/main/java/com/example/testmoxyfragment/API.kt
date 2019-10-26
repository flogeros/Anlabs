package com.example.testmoxyfragment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("inf/meteo.php")
    fun getWeatherList(@Query("tid") tid: Int): Call<List<Items>>
}