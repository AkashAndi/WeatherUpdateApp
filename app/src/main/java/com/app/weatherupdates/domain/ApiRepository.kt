package com.app.weatherupdates.domain

import com.app.weatherupdates.domain.entity.WeatherEntity
import io.reactivex.Single

interface ApiRepository {
    fun getWeather(lat: Double, lon: Double, appId: String): Single<WeatherEntity>
//    fun getWeatherForecast(lat: Double, lon: Double, appId: String, unit: String): Single<JsonObject>
}