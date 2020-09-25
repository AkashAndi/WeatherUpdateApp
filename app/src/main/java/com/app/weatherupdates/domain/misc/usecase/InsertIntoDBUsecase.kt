package com.app.weatherupdates.domain.misc.usecase

import com.app.weatherupdates.domain.BaseUseCase
import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import com.app.weatherupdates.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class InsertIntoDBUsecase @Inject constructor(
        private val repository: MiscRepository
) : BaseUseCase<Long>() {
    override fun buildSingle(data: Map<String, Any?>): Single<Long> {
        val bookMarkedLocation = data[Constants.INSERT_BOOKMARK_DB] as BookMarkedLocation
        return repository.insertBookmark(bookMarkedLocation)
    }
}