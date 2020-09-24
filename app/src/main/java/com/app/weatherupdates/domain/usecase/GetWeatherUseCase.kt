package com.app.weatherupdates.domain.usecase

import com.app.weatherupdates.BuildConfig
import com.app.weatherupdates.data.WeatherRequest
import com.app.weatherupdates.domain.ApiRepository
import com.app.weatherupdates.domain.BaseUseCase
import com.app.weatherupdates.domain.entity.WeatherEntity
import com.app.weatherupdates.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val apiRepository: ApiRepository) :
        BaseUseCase<WeatherEntity>() {
    override fun buildSingle(data: Map<String, Any?>): Single<WeatherEntity> {
        val request = data[Constants.WEATHER_REQUEST] as WeatherRequest
        return apiRepository.getWeather(request.lat,request.lon, BuildConfig.APP_ID)
    }
}