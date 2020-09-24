package com.app.weatherupdates.utils

import android.net.ConnectivityManager

fun hasNetwork(cm: ConnectivityManager) = cm.isDefaultNetworkActive