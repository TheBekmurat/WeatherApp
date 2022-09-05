package com.example.weatherapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.data.model.weather.Forecastday

@Entity(tableName = "cache")
data class SavedWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currentTemp: Double,
    val conditionText: String,
    val locationName: String,
    val localTime: String,
    val icon : Int,
    val foreCastDay: List<Forecastday>
)
