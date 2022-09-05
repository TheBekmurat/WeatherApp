package com.example.weatherapp.domain

interface ShowCurrentDateUseCase {

    fun execute(date: String,)

    class ShowCurrentDateUseCase: com.example.weatherapp.domain.ShowCurrentDateUseCase{
        override fun execute(date: String) {
        }
    }
}