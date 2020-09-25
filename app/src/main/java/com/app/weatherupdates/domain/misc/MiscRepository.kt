package com.app.weatherupdates.domain.misc

import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import io.reactivex.Single

interface MiscRepository {
    fun insertBookmark(bookMarkedLocation: BookMarkedLocation): Single<Long>
    fun deleteBookmark(id: String): Single<Boolean>
    fun getBookmark(): Single<List<BookMarkedLocation>>
}