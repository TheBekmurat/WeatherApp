package com.example.weatherapp.data.room.converter

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.SavedHour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourConverter {

    @TypeConverter
    fun fromCountryLangList(value: List<SavedHour>): String {
        val gson = Gson()
        val type = object : TypeToken<List<SavedHour>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<SavedHour> {
        val gson = Gson()
        val type = object : TypeToken<List<SavedHour>>() {}.type
        return gson.fromJson(value, type)
    }
}