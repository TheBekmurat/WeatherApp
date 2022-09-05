package com.example.weatherapp.app

import android.app.Application
import com.example.weatherapp.ui.di.AppComponent
import com.example.weatherapp.ui.di.DaggerAppComponent

class App: Application()  {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}