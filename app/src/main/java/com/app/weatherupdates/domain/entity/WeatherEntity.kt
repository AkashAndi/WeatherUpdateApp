package com.app.weatherupdates.domain.entity


import androidx.annotation.Keep

@Keep
data class WeatherEntity(
    var base: String? = null,
    var clouds: Clouds? = null,
    var cod: Int? = null,
    var dt: Int? = null,
    var id: Int? = null,
    var main: Main? = null,
    var name: String? = null,
    var sys: Sys? = null,
    var timezone: Int? = null,
    var visibility: Int? = null,
    var weather: List<Weather?>? = null,
    var wind: Wind? = null
) {
    @Keep
    data class Clouds(
        var all: Int? = null
    )

    @Keep
    data class Main(
        var feels_like: Double? = null,
        var humidity: Int? = null,
        var pressure: Int? = null,
        var temp: Double? = null,
        var temp_max: Double? = null,
        var temp_min: Double? = null
    )

    @Keep
    data class Sys(
        var country: String? = null,
        var id: Int? = null,
        var sunrise: Int? = null,
        var sunset: Int? = null,
        var type: Int? = null
    )

    @Keep
    data class Weather(
        var description: String? = null,
        var icon: String? = null,
        var id: Int? = null,
        var main: String? = null
    )

    @Keep
    data class Wind(
        var deg: Int? = null,
        var speed: Double? = null
    )
}