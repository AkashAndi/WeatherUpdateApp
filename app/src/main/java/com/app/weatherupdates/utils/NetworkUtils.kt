package com.app.weatherupdates.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi


fun hasNetwork(cm: ConnectivityManager) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    isOnline(cm)
} else {
    cm.activeNetworkInfo?.isConnected ?: false
}

@RequiresApi(Build.VERSION_CODES.M)
private fun isOnline(connectivityManager: ConnectivityManager): Boolean {
    val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }
    }
    return false
}