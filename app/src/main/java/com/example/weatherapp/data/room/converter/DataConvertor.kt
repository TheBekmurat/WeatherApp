package com.example.weatherapp.data.room.converter

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.SavedForecastDay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ForeCastConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<SavedForecastDay>): String {
        val gson = Gson()
        val type = object : TypeToken<List<SavedForecastDay>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<SavedForecastDay> {
        val gson = Gson()
        val type = object : TypeToken<List<SavedForecastDay>>() {}.type
        return gson.fromJson(value, type)
    }
}