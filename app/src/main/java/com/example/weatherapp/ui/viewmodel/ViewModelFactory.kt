package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    todayViewModelProvider: Provider<TodayViewModel>,
    weekViewModelProvider: Provider<WeekViewModel>,
    sharedViewModelProvider: Provider<SharedViewModel>
    ) : ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        TodayViewModel::class.java to todayViewModelProvider,
        WeekViewModel::class.java to weekViewModelProvider,
        SharedViewModel::class.java to sharedViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}