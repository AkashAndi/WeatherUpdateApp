package com.app.weatherupdates.core.network

import android.net.ConnectivityManager
import com.app.weatherupdates.utils.NoNetworkException
import com.app.weatherupdates.utils.hasNetwork
import okhttp3.Interceptor
import okhttp3.Response

class NetworkAvailabilityInterceptor(
        private val cm: ConnectivityManager,
        private val noNetworkMessage: String,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasNetwork(cm)) throw NoNetworkException(noNetworkMessage)
        val request = chain.request()
        val requestWithUserAgent = request.newBuilder().build()
        return chain.proceed(requestWithUserAgent)
    }
}