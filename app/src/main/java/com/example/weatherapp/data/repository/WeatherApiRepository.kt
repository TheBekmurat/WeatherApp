package com.example.weatherapp.data.repository

import com.example.weatherapp.Resource
import com.example.weatherapp.data.model.weather.WeatherModel
import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.room.dao.WeatherDao
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface WeatherApiRepository {

    suspend fun getWeatherData(city: String): Resource<Response<WeatherModel>>

    class WeatherApiRepository @Inject constructor(
        private val weatherApi: WeatherApi,
        private val weatherDao: WeatherDao
    ) :
        com.example.weatherapp.data.repository.WeatherApiRepository {

        override suspend fun getWeatherData(
            city: String,
        ): Resource<Response<WeatherModel>> =
            try {
                val weather = weatherApi.getForecast(q = city)
                Resource.Success(weather)
            } catch (e: Exception) {
                Resource.Error(e.toString())
            }
    }
}


