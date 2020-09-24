package com.app.weatherupdates.data.remote.response


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeatherResponse(
        @SerializedName("base")
        var base: String? = null,
        @SerializedName("clouds")
        var clouds: Clouds? = null,
        @SerializedName("cod")
        var cod: Int? = null,
        @SerializedName("dt")
        var dt: Int? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("main")
        var main: Main? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("sys")
        var sys: Sys? = null,
        @SerializedName("timezone")
        var timezone: Int? = null,
        @SerializedName("visibility")
        var visibility: Int? = null,
        @SerializedName("weather")
        var weather: List<Weather?>? = null,
        @SerializedName("wind")
        var wind: Wind? = null
) {
    @Keep
    data class Clouds(
            @SerializedName("all")
            var all: Int? = null
    )

    @Keep
    data class Main(
            @SerializedName("feels_like")
            var feelsLike: Double? = null,
            @SerializedName("humidity")
            var humidity: Int? = null,
            @SerializedName("pressure")
            var pressure: Int? = null,
            @SerializedName("temp")
            var temp: Double? = null,
            @SerializedName("temp_max")
            var tempMax: Double? = null,
            @SerializedName("temp_min")
            var tempMin: Double? = null
    )

    @Keep
    data class Sys(
            @SerializedName("country")
            var country: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("sunrise")
            var sunrise: Int? = null,
            @SerializedName("sunset")
            var sunset: Int? = null,
            @SerializedName("type")
            var type: Int? = null
    )

    @Keep
    data class Weather(
            @SerializedName("description")
            var description: String? = null,
            @SerializedName("icon")
            var icon: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("main")
            var main: String? = null
    )

    @Keep
    data class Wind(
            @SerializedName("deg")
            var deg: Int? = null,
            @SerializedName("speed")
            var speed: Double? = null
    )
}