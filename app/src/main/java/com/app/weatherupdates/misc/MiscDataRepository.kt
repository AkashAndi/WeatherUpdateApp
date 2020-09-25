package com.app.weatherupdates.misc

import com.app.weatherupdates.core.local.WeatherDatabase
import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import io.reactivex.Single
import javax.inject.Inject

class MiscDataRepository @Inject constructor(private val weatherDatabase: WeatherDatabase) :
        MiscRepository {

    override fun insertBookmark(bookMarkedLocation: BookMarkedLocation): Single<Long> {
        return weatherDatabase.bookmarkedLocationDao().insertItem(bookMarkedLocation)
    }

    override fun deleteBookmark(id: String): Single<Boolean> {
        return Single.create {
            weatherDatabase.bookmarkedLocationDao().deleteLocation(id)
            it.onSuccess(true)
        }
    }

    override fun getBookmark(): Single<List<BookMarkedLocation>> {
        return weatherDatabase.bookmarkedLocationDao().getLocatoinDescending()
    }

    override fun deleteAll(): Single<Boolean> {
        return Single.create {
            weatherDatabase.bookmarkedLocationDao().deleteAll()
            it.onSuccess(true)
        }
    }
}
