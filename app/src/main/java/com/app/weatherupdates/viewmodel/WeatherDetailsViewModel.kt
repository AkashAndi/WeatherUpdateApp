package com.app.weatherupdates.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import com.app.weatherupdates.BR
import com.app.weatherupdates.base.BaseObservableViewModel
import com.app.weatherupdates.core.local.AppPrefs
import com.app.weatherupdates.data.WeatherRequest
import com.app.weatherupdates.domain.api.usecase.GetWeatherUseCase
import com.app.weatherupdates.utils.Constants
import com.app.weatherupdates.utils.customSubscribe
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(
        val app: Application,
        private val appPrefs: AppPrefs,
        private val getWeatherUseCase: GetWeatherUseCase
) : BaseObservableViewModel(app) {

    var latitude = 0.0
    var longitude = 0.0

    @Bindable
    var address: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    @Bindable
    var imageUrl: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageUrl)
        }

    @Bindable
    var wind: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.wind)
        }

    @Bindable
    var acctualTemp: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.acctualTemp)
        }

    @Bindable
    var weather: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weather)
        }


    @Bindable
    var humidity: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.humidity)
        }

    @Bindable
    var pressure: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.pressure)
        }

    @Bindable
    var rain: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.pressure)
        }

    @Bindable
    var progressVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.progressVisible)
        }

    @Bindable
    var farenhiteSeleted: Boolean = appPrefs.unitSelected == Constants.UNIT_TEMP_FER
        set(value) {
            field = value
            if (value)
                appPrefs.unitSelected = Constants.UNIT_TEMP_FER
            else
                appPrefs.unitSelected = Constants.UNIT_TEMP_CELCIUS
            notifyPropertyChanged(BR.farenhiteSeleted)
        }


    /**
     * Temperature is available in Celsius, Fahrenheit and Kelvin units.
    For Fahrenheit use units=imperial
    For Celsius use units=metric
     */
    private var tempUnit = if (appPrefs.unitSelected == Constants.UNIT_TEMP_FER) {
        "Farenhite"
    } else {
        "Celcius"
    }
        get() {
            return if (appPrefs.unitSelected == Constants.UNIT_TEMP_FER) {
                "Farenhite"
            } else {
                "Celcius"
            }
        }

    fun getWeatherDetails() {
        progressVisible = true
        val data =
                mapOf(
                        Constants.WEATHER_REQUEST to WeatherRequest(
                                latitude,
                                longitude
                        )
                )
        getWeatherUseCase.execute(data).customSubscribe({
            progressVisible = false
            weather = if (it.weather == null) {
                "Weather data is not available "
            } else
                "${it.weather?.get(0)?.main}\n${it.weather?.get(0)?.description}"
            imageUrl = "http://openweathermap.org/img/w/${it.weather?.get(0)?.icon}.png"
            acctualTemp = "Actual Temerature is ${it.main?.temp} " +
                    "$tempUnit\nBut it feel like ${it.main?.feels_like} $tempUnit"
            humidity = "Humidity level is ${it.main?.humidity}"
            pressure = "Pressure is ${it.main?.pressure}"
            wind = if (it.wind == null) {
                "Wind data is not available "
            } else {
                "Wind Speed is ${it.wind?.speed}\nWind Angle is ${it.wind?.deg}"
            }
        }, {
            progressVisible = false
            _errorLiveData.value = it
        }).collect()
    }

}