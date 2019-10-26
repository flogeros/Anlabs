package com.example.testmoxyfragment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DBHelper(context: Context) {
    companion object {
        private var instance: DBHelper? = null

        fun getInstance(context: Context): DBHelper {
            synchronized(DBHelper::class.java) {
                if (instance == null)
                    instance = DBHelper(context)
            }
            return instance!!
        }
    }

    private val dbHelper: SQLHelper
    private val db: SQLiteDatabase

    init {
        dbHelper = SQLHelper(context)
        db = dbHelper.writableDatabase
    }

    fun clearTable(table_name : String) {
        dbHelper.clearTable(db, table_name)
    }

    fun getAllWeather() : ArrayList<Items> {
        val result = arrayListOf<Items>()

        val cursor = db.query(SQLHelper.WEATHER_TABLE, arrayOf("*"), null, null, null, null, null)

        val date = cursor.getColumnIndex(SQLHelper.WEATHER_DATE)
        val tod = cursor.getColumnIndex(SQLHelper.WEATHER_TOD)
        val pressure = cursor.getColumnIndex(SQLHelper.WEATHER_PRESSURE)
        val temp = cursor.getColumnIndex(SQLHelper.WEATHER_TEMP)
        val humidity = cursor.getColumnIndex(SQLHelper.WEATHER_HUMIDITY)
        val wind = cursor.getColumnIndex(SQLHelper.WEATHER_WIND)
        val cloud = cursor.getColumnIndex(SQLHelper.WEATHER_CLOUD)
        val creationDate = cursor.getColumnIndex(SQLHelper.WEATHER_CREATION_DATE)
        val id = cursor.getColumnIndex("id")

        while (cursor.moveToNext())
            result.add(
                Items(
                    cursor.getString(date),
                    cursor.getString(tod),
                    cursor.getString(pressure),
                    cursor.getString(temp),
                    cursor.getString(humidity),
                    cursor.getString(wind),
                    cursor.getString(cloud),
                    cursor.getString(creationDate),
                    cursor.getInt(id)
                )
            )

        return result
    }

    fun addAllWeather(weather : ArrayList<Items>) {
        for (i in 0..weather.size - 1) {
            addWeather(weather[i])
        }
    }

    fun addWeather(weather : Items) {
        val contentValues = ContentValues()
        contentValues.put(SQLHelper.WEATHER_DATE, weather.date)
        contentValues.put(SQLHelper.WEATHER_TOD, weather.timeOfDay)
        contentValues.put(SQLHelper.WEATHER_PRESSURE, weather.pressure)
        contentValues.put(SQLHelper.WEATHER_TEMP, weather.temp)
        contentValues.put(SQLHelper.WEATHER_HUMIDITY, weather.humidity)
        contentValues.put(SQLHelper.WEATHER_WIND, weather.wind)
        contentValues.put(SQLHelper.WEATHER_CLOUD, weather.cloud)
        contentValues.put(SQLHelper.WEATHER_CREATION_DATE, weather.creationDate)
        db.insert(SQLHelper.WEATHER_TABLE, null, contentValues)
    }
}