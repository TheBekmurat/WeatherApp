package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Resource
import com.example.weatherapp.data.model.uistate.UiState
import com.example.weatherapp.data.repository.WeatherApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(private val weatherApiRepository: WeatherApiRepository) :
    ViewModel() {

    val mutableLiveData = MutableLiveData<UiState>()

    fun getWeather(city: String, days: Int) {
        viewModelScope.launch {
            mutableLiveData.value = UiState(isLoading = true)
            when (val data = weatherApiRepository.getWeatherData(city)) {
                is Resource.Success -> mutableLiveData.value = UiState(weatherModel = data.data?.body())
                is Resource.Error -> mutableLiveData.value = UiState(error = "Smth hpnd")
            }
        }
    }
}