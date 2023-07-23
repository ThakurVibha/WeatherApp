package com.example.androidassignemnt2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidassignemnt2.repository.WeatherReportRepository
import com.example.androidassignemnt2.room.database.WeatherReportDatabase
import com.example.androidassignemnt2.room.entity.WeatherReportEntity
import com.example.androidassignemnt2.ui.WeatherReportActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherReportViewModel(private var weatherReportRepository: WeatherReportRepository) :
    ViewModel() {
    fun sendCityName(context: WeatherReportActivity, cityName: String, API_KEY: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherReportRepository.getWeatherReportResponse(context, cityName, API_KEY)

        }
    }

    fun getLocalWeatherReport() {
        CoroutineScope(Dispatchers.IO).launch {
            weatherReportRepository.getDataFromLocalDatabase()
        }
    }

    fun insertWeatherDataToDatabase(it: WeatherReportEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherReportRepository.insertDataToDatabase(it)
        }

    }

    val weatherReport: LiveData<WeatherReportEntity>
        get() = weatherReportRepository.getWeatherReportData
    val weatherErrorReport: LiveData<String>
        get() = weatherReportRepository.getWeatherReportError

    val weatherReportException: LiveData<String>
        get() = weatherReportRepository.getWeatherReportException

    val getWeatherReport: LiveData<WeatherReportEntity>
        get() = weatherReportRepository.getLocalWeatherReport

    val getInsertedData: MutableLiveData<WeatherReportEntity?>
        get() = weatherReportRepository.getInsertedData

}



