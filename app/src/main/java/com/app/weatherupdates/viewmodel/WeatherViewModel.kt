package com.app.weatherupdates.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.weatherupdates.BR
import com.app.weatherupdates.base.BaseObservableViewModel
import com.app.weatherupdates.data.WeatherRequest
import com.app.weatherupdates.domain.api.usecase.GetWeatherUseCase
import com.app.weatherupdates.domain.entity.WeatherEntity
import com.app.weatherupdates.domain.misc.usecase.DeleteBookmarkUsecase
import com.app.weatherupdates.domain.misc.usecase.GetBookMarkUsecase
import com.app.weatherupdates.domain.misc.usecase.InsertIntoDBUsecase
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import com.app.weatherupdates.utils.Constants
import com.app.weatherupdates.utils.customSubscribe
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
        val app: Application,
        private val getWeatherUseCase: GetWeatherUseCase,
        private val getBookMarkUsecase: GetBookMarkUsecase,
        private val insertIntoDBUsecase: InsertIntoDBUsecase,
        private val deleteBookmarkUsecase: DeleteBookmarkUsecase) : BaseObservableViewModel(app) {

    @Bindable
    var progressVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.progressVisible)
        }

    var bookmarkLiveData = MutableLiveData<MutableList<BookMarkedLocation>>()

    private val _weatherLiveData = MutableLiveData<WeatherEntity?>()
    val weatherLiveData: LiveData<WeatherEntity?> get() = _weatherLiveData

    fun insertIntoDB(
            data: Map<String, BookMarkedLocation>
    ) {
        progressVisible = true
        insertIntoDBUsecase.execute(data).customSubscribe({
            getBookmark()
        }, {
            progressVisible = false
        }).collect()
    }

    private fun getBookmark() {
        getBookMarkUsecase.execute().customSubscribe({
            progressVisible = false
            bookmarkLiveData.value = it.toMutableList()
        }, {
            _errorLiveData.value = it
            progressVisible = false
        })
    }

    fun getWeatherDetails(lattitud: Double, longitude: Double) {
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