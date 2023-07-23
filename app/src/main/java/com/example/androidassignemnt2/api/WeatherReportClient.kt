package com.example.androidassignemnt2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherReportClient {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = "cb1659e0cd6187347cdd0b4dc7e75320"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}