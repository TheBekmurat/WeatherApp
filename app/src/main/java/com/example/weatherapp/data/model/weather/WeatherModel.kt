package com.example.weatherapp.data.model.weather

import com.example.weatherapp.data.model.weather.Current
import com.example.weatherapp.data.model.weather.Forecast
import com.example.weatherapp.data.model.weather.Location

data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)