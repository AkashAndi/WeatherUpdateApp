package com.app.weatherupdates.data.remote

import com.app.weatherupdates.data.remote.response.WeatherResponse
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appId") appId: String, @Query("unit") unit: String?): Single<WeatherResponse>

    @GET("forecast")
    fun getWeatherForecast(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appId") appId: String, @Query("unit") unit: String): Single<JsonObject>

}