package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.weather.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = "5eb0ea7e509942d680d73755221006",
        @Query("q") q: String,
        @Query("days") days: Int = 9,
        @Query("aqi") aqi: String = "yes",
    ): Response<WeatherModel>
}