package com.example.weatherapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.weatherapp.data.model.cache.Cache
import com.example.weatherapp.data.room.converter.ForeCastConverter
import com.example.weatherapp.data.room.converter.HourConverter
import com.example.weatherapp.data.room.dao.WeatherDao

@Database(entities = [Cache::class],version = 1)
@TypeConverters(ForeCastConverter::class,HourConverter::class)
abstract class Room: RoomDatabase() {
    abstract fun weatherDao():  WeatherDao
}