package com.app.weatherupdates.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.weatherupdates.BR
import com.app.weatherupdates.base.BaseObservableViewModel
import com.app.weatherupdates.data.WeatherRequest
import com.app.weatherupdates.domain.entity.WeatherEntity
import com.app.weatherupdates.domain.usecase.GetWeatherUseCase
import com.app.weatherupdates.utils.Constants
import com.app.weatherupdates.utils.customSubscribe
import javax.inject.Inject

class WeatherViewModel @Inject constructor(val app: Application,
                                           private val getWeatherUseCase: GetWeatherUseCase) : BaseObservableViewModel(app) {

    @Bindable
    var progressVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.progressVisible)
        }


    private val _weatherLiveData = MutableLiveData<WeatherEntity?>()
    val weatherLiveData: LiveData<WeatherEntity?> get() = _weatherLiveData

    fun getWeather(lattitud: Double, longitude: Double) {
        progressVisible = true
        val data =
                mapOf(
                        Constants.WEATHER_REQUEST to WeatherRequest(
                                lattitud,
                                longitude
                        )
                )
        getWeatherUseCase.execute(data).customSubscribe({
            progressVisible = false
            _weatherLiveData.value = it
        }, {
            progressVisible = false
            _errorLiveData.value = it
        }).collect()
    }

}