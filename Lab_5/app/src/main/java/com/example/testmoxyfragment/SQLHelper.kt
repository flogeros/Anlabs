package com.example.testmoxyfragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        val DB_NAME = "weather.db"
        val DB_VERSION = 1

        val WEATHER_TABLE = "weather"
        val WEATHER_DATE = "date"
        val WEATHER_TOD = "tod"
        val WEATHER_PRESSURE = "pressure"
        val WEATHER_TEMP = "temp"
        val WEATHER_HUMIDITY= "humidity"
        val WEATHER_WIND = "wind"
        val WEATHER_CLOUD = "cloud"
        val WEATHER_CREATION_DATE = "creationDate"

        val CREATE_WEATHER_TABLE = "CREATE TABLE " + WEATHER_TABLE +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WEATHER_DATE + " VARCHAR(64), " +
                WEATHER_TOD + " VARCHAR(64), " +
                WEATHER_PRESSURE + " VARCHAR(64), " +
                WEATHER_TEMP + " VARCHAR(64), " +
                WEATHER_HUMIDITY + " VARCHAR(64), " +
                WEATHER_WIND + " VARCHAR(64), " +
                WEATHER_CLOUD + " VARCHAR(255), " +
                WEATHER_CREATION_DATE + " VARCHAR(255))"
    }

    fun clearTable(db : SQLiteDatabase, table_name : String) {
        db.execSQL("delete from "+ table_name);
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_WEATHER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}