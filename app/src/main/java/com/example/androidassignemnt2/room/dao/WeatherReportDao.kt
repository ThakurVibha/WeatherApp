package com.example.androidassignemnt2.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidassignemnt2.room.entity.WeatherReportEntity

@Dao
interface WeatherReportDao {

    @Insert
    suspend fun insertWeatherData(data: WeatherReportEntity)

    @Query("SELECT * FROM weather_report")
    suspend fun getWeatherDataFromDatabase(): WeatherReportEntity
}