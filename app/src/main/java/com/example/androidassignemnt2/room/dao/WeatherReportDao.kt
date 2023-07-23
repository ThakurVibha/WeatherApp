package com.example.androidassignemnt2.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidassignemnt2.room.entity.WeatherReportEntity

@Dao
interface WeatherReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(data: WeatherReportEntity)
    @Query("SELECT * FROM weather_report ORDER BY timestamp DESC LIMIT 1")
    suspend fun getWeatherDataFromDatabase(): WeatherReportEntity
    @Query("SELECT * FROM weather_report WHERE id = :id")
    suspend fun getWeatherReportById(id: Int): WeatherReportEntity?
    @Query("DELETE FROM weather_report")
    suspend fun deleteAllWeatherReports()
}