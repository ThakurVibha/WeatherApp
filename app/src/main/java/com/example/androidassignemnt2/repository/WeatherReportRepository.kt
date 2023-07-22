package com.example.androidassignemnt2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidassignemnt2.api.WeatherReportService
import com.example.androidassignemnt2.data.WeatherReport
import com.example.androidassignemnt2.room.database.WeatherReportDatabase
import com.example.androidassignemnt2.room.entity.WeatherReportEntity

class WeatherReportRepository(
    private var weatherReportService: WeatherReportService,
    private var weatherReportDatabase: WeatherReportDatabase
) {

    private val weatherReportData = MutableLiveData<WeatherReport>()

    val getWeatherReportData: LiveData<WeatherReport>
        get() = weatherReportData


    suspend fun getWeatherReportResponse(toString: String, API_KEY: String?) {
        try {
            val result = weatherReportService.getWeatherReport(toString, API_KEY.toString())

            Log.d("getResult", "$result")
            if (result.body() != null) {
                //saving data to database
                weatherReportData.let {
                    val weatherData = result.body()
                    weatherData?.clouds?.let { it1 ->
                        weatherData?.main?.let { it2 ->
                            weatherData?.coord?.let { it3 ->
                                weatherData?.rain?.let { it4 ->
                                    weatherData?.sys?.let { it5 ->
                                        weatherData?.weather?.let { it6 ->
                                            weatherData?.wind?.let { it7 ->
                                                WeatherReportEntity(
                                                    id = weatherData?.id ?: 0,
                                                    base = weatherData?.base ?: "",
                                                    clouds = it1,
                                                    cod = weatherData?.cod ?: 0,
                                                    coord = it3,
                                                    dt = weatherData?.dt ?: 0,
                                                    main = it2,
                                                    name = weatherData?.name ?: "",
                                                    rain = it4,
                                                    sys = it5,
                                                    timezone = weatherData?.timezone ?: 0,
                                                    visibility = weatherData?.visibility ?: 0,
                                                    weather = it6,
                                                    wind = it7
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }?.let { it2 ->
                        weatherReportDatabase.weatherDao()
                            .insertWeatherData(
                                it2
                            )
                    }
                }
                weatherReportData.postValue(result.body())
            }
        } catch (e: Exception) {
            Log.d("exception", "${e.printStackTrace()}")
            Log.d("exception", "$e")

        }

    }
}
