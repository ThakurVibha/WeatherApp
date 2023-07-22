package com.example.androidassignemnt2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidassignemnt2.repository.WeatherReportRepository


class WeatherReportViewModelFactory(private val weatherReportRepository: WeatherReportRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherReportViewModel(weatherReportRepository) as T
    }
}