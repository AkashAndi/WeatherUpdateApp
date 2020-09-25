package com.app.weatherupdates.core.network

import androidx.annotation.CallSuper
import com.app.weatherupdates.BuildConfig
import com.app.weatherupdates.WeatherUpdatesApp
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class CommonService<T> : BaseService<T>() {

    @Inject
    lateinit var networkAvailabilityInterceptor: NetworkAvailabilityInterceptor

    @Inject
    lateinit var weatherUpdatesApp: WeatherUpdatesApp

    @Inject
    lateinit var netErrInterceptor: NetworkErrorIntercepter

    override val baseUrl = BuildConfig.BASE_URL

    @CallSuper
    override fun handleOkHttpBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return if (BuildConfig.DEBUG) {
            return super.handleOkHttpBuilder(builder)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(networkAvailabilityInterceptor)
//                    .addInterceptor(ChuckInterceptor(weatherUpdatesApp.baseContext))
                    .addInterceptor(netErrInterceptor)
        } else {
            super.handleOkHttpBuilder(builder)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(networkAvailabilityInterceptor)
                    .addInterceptor(netErrInterceptor)
        }
    }
}