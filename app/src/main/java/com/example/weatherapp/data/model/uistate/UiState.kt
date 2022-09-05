package com.example.weatherapp.data.model.uistate

import com.example.weatherapp.data.model.weather.WeatherModel

data class UiState(
    val isLoading: Boolean = false,
    val weatherModel: WeatherModel? = null,
    val error: String? = ""
)