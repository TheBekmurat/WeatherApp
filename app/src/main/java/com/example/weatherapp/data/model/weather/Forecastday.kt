package com.example.weatherapp.data.model.weather

import androidx.room.Embedded

data class Forecastday(
    @Embedded
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)