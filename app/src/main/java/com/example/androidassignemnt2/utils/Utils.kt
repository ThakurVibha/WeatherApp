package com.example.androidassignemnt2.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.animation.Animation
import android.widget.TextView
import android.widget.Toast

object Utils {

    fun TextView.shakeView(shakeAnimation: Animation) {
        startAnimation(shakeAnimation)
        requestFocus()
    }

    // Extension function to show a Toast message with the given text and duration
    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }

    // Extension function to show message when internet is not available
    fun Context.isInternetAvailable(showToast: Boolean = true): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                // for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> {
                    if (showToast) {
                        showToast("No internet connection")
                    }
                    false
                }
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return if (networkInfo.isConnected) {
                true
            } else {
                if (showToast) {
                    showToast("No internet connection")
                }
                false
            }
        }
    }


}