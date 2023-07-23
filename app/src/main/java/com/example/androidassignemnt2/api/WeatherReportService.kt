package com.example.androidassignemnt2.api

import com.example.androidassignemnt2.api.WeatherReportClient.BASE_URL
import com.example.androidassignemnt2.data.WeatherReport
import com.example.androidassignemnt2.room.entity.WeatherReportEntity
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherReportService {

    @GET("weather")
    suspend fun getWeatherReport(
        @Query("q") q: String, @Query("appid") apiKey: String
    ): retrofit2.Response<WeatherReportEntity>

}