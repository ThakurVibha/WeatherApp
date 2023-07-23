package com.example.androidassignemnt2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidassignemnt2.api.WeatherReportService
import com.example.androidassignemnt2.data.WeatherReport
import com.example.androidassignemnt2.room.database.WeatherReportDatabase
import com.example.androidassignemnt2.room.entity.WeatherReportEntity
import com.example.androidassignemnt2.ui.WeatherReportActivity
import com.example.androidassignemnt2.utils.Utils.isInternetAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherReportRepository(
    private var weatherReportService: WeatherReportService,
    private var weatherReportDatabase: WeatherReportDatabase
) {
    private val weatherReportData = MutableLiveData<WeatherReportEntity>()
    private val weatherReportError = MutableLiveData<String>()
    private val weatherReportException = MutableLiveData<String>()
    private val insertToLocalDB = MutableLiveData<WeatherReportEntity?>()
    private val localWeatherReportData = MutableLiveData<WeatherReportEntity>()

    val getWeatherReportData: LiveData<WeatherReportEntity>
        get() = weatherReportData
    val getWeatherReportError: LiveData<String>
        get() = weatherReportError

    val getWeatherReportException: LiveData<String>
        get() = weatherReportException
    val getInsertedData: MutableLiveData<WeatherReportEntity?>
        get() = insertToLocalDB
    val getLocalWeatherReport: LiveData<WeatherReportEntity>
        get() = localWeatherReportData


    suspend fun getWeatherReportResponse(
        context: WeatherReportActivity,
        cityName: String,
        API_KEY: String?
    ) {
        try {
            val result = weatherReportService.getWeatherReport(cityName, API_KEY.toString())
            Log.d("getResult", "$result")
            if (result.body() != null) {
                if (result.isSuccessful) {
                    weatherReportData.postValue(result.body())
                } else {
                    weatherReportError.postValue(result.body().toString())
                }

            } else {
                weatherReportError.postValue(result.body().toString())
            }
        } catch (e: Exception) {
            weatherReportException.postValue(e.message)
            Log.d("exception", "${e.printStackTrace()}")
        }
    }

    fun insertDataToDatabase(it: WeatherReportEntity?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (it != null) {
                it.timestamp = System.currentTimeMillis() // Setting the timestamp before insertion
                weatherReportDatabase.weatherDao().insertWeatherData(it)
                insertToLocalDB.postValue(it)
                Log.e("insertWeatherDataToRoom", "$it")

            }
        }
    }

    suspend fun getDataFromLocalDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val localWeatherReport = weatherReportDatabase.weatherDao().getWeatherDataFromDatabase()
            localWeatherReportData.postValue(localWeatherReport)
        }

    }

}

