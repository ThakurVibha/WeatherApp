package com.example.androidassignemnt2.room.databaseutils

import androidx.room.TypeConverter
import com.example.androidassignemnt2.data.Clouds
import com.example.androidassignemnt2.data.Coord
import com.example.androidassignemnt2.data.Main
import com.example.androidassignemnt2.data.Rain
import com.example.androidassignemnt2.data.Sys
import com.example.androidassignemnt2.data.Weather
import com.example.androidassignemnt2.data.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseTypeConvertors {

    private val gson = Gson()

    @TypeConverter
    fun fromCoord(coord: Coord): String {
        return gson.toJson(coord)
    }

    @TypeConverter
    fun toCoord(coordString: String): Coord {
        val type = object : TypeToken<Coord>() {}.type
        return gson.fromJson(coordString, type)
    }

    @TypeConverter
    fun fromWeatherList(weatherList: List<Weather>): String {
        return gson.toJson(weatherList)
    }

    @TypeConverter
    fun toWeatherList(weatherListString: String): List<Weather> {
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(weatherListString, type)
    }

    @TypeConverter
    fun fromMain(main: Main): String {
        return gson.toJson(main)
    }

    @TypeConverter
    fun toMain(mainString: String): Main {
        val type = object : TypeToken<Main>() {}.type
        return gson.fromJson(mainString, type)
    }

    @TypeConverter
    fun fromWind(wind: Wind): String {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun toWind(windString: String): Wind {
        val type = object : TypeToken<Wind>() {}.type
        return gson.fromJson(windString, type)
    }

    @TypeConverter
    fun fromClouds(clouds: Clouds): String {
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun fromRain(rain: Rain?): String? {
        return gson.toJson(rain)
    }

    @TypeConverter
    fun toRain(rainString: String?): Rain? {
        val type = object : TypeToken<Rain>() {}.type
        return gson.fromJson(rainString, type)
    }

    @TypeConverter
    fun toClouds(cloudsString: String): Clouds {
        val type = object : TypeToken<Clouds>() {}.type
        return gson.fromJson(cloudsString, type)
    }

    @TypeConverter
    fun fromSys(sys: Sys): String {
        return gson.toJson(sys)
    }

    @TypeConverter
    fun toSys(sysString: String): Sys {
        val type = object : TypeToken<Sys>() {}.type
        return gson.fromJson(sysString, type)
    }
}