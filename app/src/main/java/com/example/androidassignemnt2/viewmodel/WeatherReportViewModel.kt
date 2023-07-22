package com.example.androidassignemnt2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidassignemnt2.data.WeatherReport
import com.example.androidassignemnt2.repository.WeatherReportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherReportViewModel(private var weatherReportRepository: WeatherReportRepository) :
    ViewModel() {
    fun sendCityName(toString: String, API_KEY: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherReportRepository.getWeatherReportResponse(toString, API_KEY)

        }
    }

    val weatherReport: LiveData<WeatherReport>
        get() = weatherReportRepository.getWeatherReportData


}



