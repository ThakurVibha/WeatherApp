package com.example.androidassignemnt2.app

import android.app.Application
import com.example.androidassignemnt2.api.WeatherReportClient
import com.example.androidassignemnt2.api.WeatherReportService
import com.example.androidassignemnt2.repository.WeatherReportRepository
import com.example.androidassignemnt2.room.database.WeatherReportDatabase

class WeatherApp : Application() {

    lateinit var weatherReportRepository: WeatherReportRepository

    override fun onCreate() {
        super.onCreate()
        initialiseApp()
    }

    private fun initialiseApp() {

        val weatherReportService =
            WeatherReportClient.getInstance().create(WeatherReportService::class.java)

        val weatherReportDatabase= WeatherReportDatabase.getDatabase(applicationContext)
         weatherReportRepository=WeatherReportRepository(weatherReportService, weatherReportDatabase)

    }
}