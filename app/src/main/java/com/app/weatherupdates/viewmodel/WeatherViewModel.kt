package com.app.weatherupdates.viewmodel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.app.weatherupdates.BR
import com.app.weatherupdates.base.BaseObservableViewModel
import com.app.weatherupdates.domain.misc.usecase.DeleteBookmarkUsecase
import com.app.weatherupdates.domain.misc.usecase.GetBookMarkUsecase
import com.app.weatherupdates.domain.misc.usecase.InsertIntoDBUsecase
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import com.app.weatherupdates.utils.Constants
import com.app.weatherupdates.utils.customSubscribe
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
        val app: Application,
        private val getBookMarkUsecase: GetBookMarkUsecase,
        private val insertIntoDBUsecase: InsertIntoDBUsecase,
        private val deleteBookmarkUsecase: DeleteBookmarkUsecase
) : BaseObservableViewModel(app) {

    @Bindable
    var progressVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.progressVisible)
        }

    var bookmarkLiveData = MutableLiveData<MutableList<BookMarkedLocation>>()

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

    fun getBookmark() {
        getBookMarkUsecase.execute().customSubscribe({
            progressVisible = false
            bookmarkLiveData.value = it.toMutableList()
        }, {
            _errorLiveData.value = it
            progressVisible = false
        }).collect()
    }

    fun deleteLocation(timeStamp: String) {
        progressVisible = true
        val data = mapOf(Constants.DELETE_BOOKMARK_DB to timeStamp)
        deleteBookmarkUsecase.execute(data).customSubscribe({
            progressVisible = false
            getBookmark()
        }, {
            progressVisible = false
        }).collect()
    }
}