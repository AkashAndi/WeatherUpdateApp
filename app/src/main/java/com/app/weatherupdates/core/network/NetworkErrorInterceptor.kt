package com.app.weatherupdates.core.network

import android.app.Application
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NetworkErrorIntercepter(
        val app: Application,
        val gson: Gson
) :
        Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.request().let { chain.proceed(it) }
        return if (response.isSuccessful) {
            val body: String = response.body?.string()?.let {
                gson.fromJson(it, Response::class.java)
                it
            } ?: UNKNOWN_ERROR
            response.newBuilder()
                    .body(body.toResponseBody("application/json".toMediaTypeOrNull()))
                    .build()
        } else {
            response.newBuilder()
                    .body(
                            (response.body?.string() ?: UNKNOWN_ERROR
                                    ).toResponseBody("application/json".toMediaTypeOrNull())
                    )
                    .build()
        }
    }

    companion object {
        const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
    }
}