package com.example.androidassignemnt2.room.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidassignemnt2.data.Clouds
import com.example.androidassignemnt2.data.Coord
import com.example.androidassignemnt2.data.Main
import com.example.androidassignemnt2.data.Rain
import com.example.androidassignemnt2.data.Sys
import com.example.androidassignemnt2.data.Weather
import com.example.androidassignemnt2.data.Wind
import com.example.androidassignemnt2.room.databaseutils.DatabaseTypeConvertors

@Entity(tableName = "weather_report")
data class WeatherReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val base: String? = "",
    @TypeConverters(DatabaseTypeConvertors::class)
    val clouds: Clouds,
    val cod: Int? = 0,
    @TypeConverters(DatabaseTypeConvertors::class)
    val coord: Coord,
    val dt: Int? = 0,
    @TypeConverters(DatabaseTypeConvertors::class)
    val main: Main,
    val name: String? = "",
    @TypeConverters(DatabaseTypeConvertors::class)
    val rain: Rain?,
    @TypeConverters(DatabaseTypeConvertors::class)
    val sys: Sys,
    val timezone: Int? = 0,
    val visibility: Int? = 0,
    @TypeConverters(DatabaseTypeConvertors::class)
    val weather: List<Weather>,
    @TypeConverters(DatabaseTypeConvertors::class)
    val wind: Wind,
    var timestamp: Long = 0

)
