package com.movieapp.huxymovies.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

class NetworkUtility(internal var context: Context) : Activity() {
    lateinit var connectivityManager: ConnectivityManager
    var connected = false

    /**
     * This method checks for network on the device.
     * @return
     */
    val isOnline: Boolean
        get() {
            try {
                connectivityManager = context
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isConnected && networkInfo.isConnected
                return connected

            } catch (e: Exception) {
                Log.v("connectivity", e.toString())
            }

            return connected
        }
}
