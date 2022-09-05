package com.example.weatherapp.data.room

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.room.dao.WeatherDao

interface CacheDataSource {

    fun weatherDao(): WeatherDao

    class Base(context: Context): CacheDataSource{

        private val room = Room.databaseBuilder(context,
        com.example.weatherapp.data.room.Room::class.java,"database").build()

        override fun weatherDao(): WeatherDao = room.weatherDao()
    }
}