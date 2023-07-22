package com.example.androidassignemnt2.api

import com.example.androidassignemnt2.api.WeatherReportClient.BASE_URL
import com.example.androidassignemnt2.data.WeatherReport
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherReportService {

    @GET("weather")
    suspend fun getWeatherReport(
        @Query("q") q: String, @Query("appid") apiKey: String
    ): retrofit2.Response<WeatherReport>

//        @GET("forecast")
//        fun getWeatherForecast(
//            @Query("q") location: String,
//            @Query("cnt") numberOfDays: Int,
//            @Query("appid") apiKey: String = ApiClient.API_KEY
//        ): Response<WeatherForecastResponse>


}