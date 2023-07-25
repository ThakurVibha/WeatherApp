package com.example.androidassignemnt2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidassignemnt2.R
import com.example.androidassignemnt2.api.WeatherReportClient.API_KEY
import com.example.androidassignemnt2.app.WeatherApp
import com.example.androidassignemnt2.databinding.ActivityWeatherReportBinding
import com.example.androidassignemnt2.room.entity.WeatherReportEntity
import com.example.androidassignemnt2.utils.Utils.hideKeyboard
import com.example.androidassignemnt2.utils.Utils.isInternetAvailable
import com.example.androidassignemnt2.utils.Utils.shakeView
import com.example.androidassignemnt2.utils.Utils.showToast
import com.example.androidassignemnt2.viewmodel.WeatherReportViewModel
import com.example.androidassignemnt2.viewmodel.WeatherReportViewModelFactory
import java.text.DecimalFormat


class WeatherReportActivity : AppCompatActivity() {
    private lateinit var weatherReportBinding: ActivityWeatherReportBinding
    private lateinit var weatherReportViewModel: WeatherReportViewModel
    private lateinit var shake: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isInternetAvailable()) {
            showToast("Internet is not available")
        }
        weatherReportBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_weather_report)
        shake = AnimationUtils.loadAnimation(this, R.anim.shake)
        initialiseViewModel()
        makeApiCall()
        setObserver()
    }


    private fun initialiseViewModel() {
        val weatherReportRepository = (application as WeatherApp).weatherReportRepository
        weatherReportViewModel =
            ViewModelProvider(this, WeatherReportViewModelFactory(weatherReportRepository)).get(
                WeatherReportViewModel::class.java
            )
    }

    private fun validation(): Boolean {
        if (weatherReportBinding.etEnterCity.text.toString().isBlank()
        ) {
            weatherReportBinding.etEnterCity.error = "Please enter valid city name!"
            weatherReportBinding.etEnterCity.shakeView(shake)
            return false
        }
        if (weatherReportBinding.etEnterCity.text.toString().isEmpty()
        ) {
            weatherReportBinding.etEnterCity.error = "Please enter valid city name!"
            weatherReportBinding.etEnterCity.shakeView(shake)
            return false
        }
        return true
    }

    private fun makeApiCall() {

        initialiseTextWatcher()
        weatherReportBinding.ivSearch.setOnClickListener {
            if (!isInternetAvailable()) {
                showToast("Internet is not available")
            } else {
                if (validation()) {
                    weatherReportViewModel.sendCityName(
                        this,
                        weatherReportBinding.etEnterCity.text.toString(),
                        API_KEY
                    )
                }
            }
        }
        weatherReportViewModel.getLocalWeatherReport()
    }

    private fun initialiseTextWatcher() {

        weatherReportBinding.etEnterCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        if (validation()) {
                            weatherReportViewModel.sendCityName(
                                this@WeatherReportActivity,
                                s.toString(),
                                API_KEY
                            )
                        }
                    }
                }
            }

        })
    }

    private fun setObserver() {
        weatherReportViewModel.weatherReport.observe(this, Observer {
            weatherReportViewModel.insertWeatherDataToDatabase(it)
            weatherReportBinding.clMainWeatherLayout.visibility = View.VISIBLE
            weatherReportBinding.ivNoDataFoudn.visibility = View.GONE
            weatherReportBinding.tvEnterValidCityName.visibility = View.GONE
            setUpViews(it)

        })
        weatherReportViewModel.weatherErrorReport.observe(this, Observer {
            weatherReportBinding.clMainWeatherLayout.visibility = View.GONE
            weatherReportBinding.ivNoDataFoudn.visibility = View.VISIBLE
            weatherReportBinding.tvEnterValidCityName.visibility = View.VISIBLE
        })
        weatherReportViewModel.weatherReportException.observe(this, Observer {
        })
        weatherReportViewModel.getInsertedData.observe(this, Observer {
            if (it != null) {
                Log.e("getInsertedData", "$it")
            }
        })

        weatherReportViewModel.getWeatherReport.observe(this, Observer {
            if (it != null) {
                Log.e("getWeatherDataFromRoom", "$it")
                setUpViews(it)
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun setUpViews(it: WeatherReportEntity) {
        weatherReportBinding.tvWindValue.text = "${it.wind.speed.toString()}/h"
        weatherReportBinding.tvVisibility.text = "${it.visibility.toString()}km"
        it.main.let {
            weatherReportBinding.tvHumidity.text = "${it.humidity.toString()}%"
            val temperatureToCel = kelvinToCelsius(it.temp.toDouble())
            val formattedTemp = formatTemperature(temperatureToCel)
            weatherReportBinding.tvTemperature.text = "$formattedTemp\u00B0"
        }
        it.weather.forEach {
            weatherReportBinding.tvMain.text = it.main
        }
        weatherReportBinding.tvCityName.text = it.name

    }

    private fun kelvinToCelsius(temperatureInKelvin: Double): Double {
        return temperatureInKelvin - 273.15
    }

    private fun formatTemperature(temperature: Double): String {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(temperature)
    }
}