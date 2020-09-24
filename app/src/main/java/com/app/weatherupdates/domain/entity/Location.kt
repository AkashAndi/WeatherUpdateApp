package com.app.weatherupdates.domain.entity


import androidx.annotation.Keep

@Keep
data class Location(
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)