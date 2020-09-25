package com.app.weatherupdates.data

import com.app.weatherupdates.core.local.AppPrefs
import com.app.weatherupdates.core.network.CommonService
import com.app.weatherupdates.data.remote.ApiService
import com.app.weatherupdates.data.remote.response.WeatherResponse
import com.app.weatherupdates.domain.api.ApiRepository
import com.app.weatherupdates.domain.entity.WeatherEntity
import io.reactivex.Single
import javax.inject.Inject

class ApiDataRepository @Inject constructor(
        private val appPrefs: AppPrefs
) : CommonService<ApiService>(), ApiRepository {

    override fun getWeather(lat: Double, lon: Double, appId: String): Single<WeatherEntity> {
        return networkService.getWeather(lat, lon, appId, appPrefs.unitSelected).map {
            WeatherEntity(it.base, mapClouds(it.clouds), it.cod, it.dt, it.id, mapMain(it.main), it.name)
        }
    }

    private fun mapMain(main: WeatherResponse.Main?) = WeatherEntity.Main(main?.feelsLike,
            main?.humidity, main?.pressure, main?.temp, main?.tempMax, main?.tempMin)

    private fun mapClouds(clouds: WeatherResponse.Clouds?) = WeatherEntity.Clouds(clouds?.all)

    override val baseClass = ApiService::class.java

}