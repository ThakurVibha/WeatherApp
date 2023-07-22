package com.example.androidassignemnt2.app

import android.app.Application
import com.example.androidassignemnt2.api.WeatherReportClient
import com.example.androidassignemnt2.api.WeatherReportService
import com.example.androidassignemnt2.repository.WeatherReportRepository
import com.example.androidassignemnt2.room.database.WeatherReportDatabase

class WeatherApp : Application() {

    //created repository reference as a singleton and it will be accessible everywhere
    //This ensures that all ViewModel instances have access to the same repository
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