package com.example.weatherapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.model.SavedWeather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM cache")
    suspend fun getAll(): LiveData<List<SavedWeather>>

    @Insert
    suspend fun insert(cache: SavedWeather)

    @Delete
    suspend fun delete(cache: SavedWeather)
}