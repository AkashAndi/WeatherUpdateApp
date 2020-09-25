package com.app.weatherupdates.domain.misc.usecase

import com.app.weatherupdates.domain.BaseUseCase
import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import io.reactivex.Single
import javax.inject.Inject

class GetBookMarkUsecase @Inject constructor(
    private val repository: MiscRepository
) : BaseUseCase<List<BookMarkedLocation>>() {
    override fun buildSingle(data: Map<String, Any?>): Single<List<BookMarkedLocation>> {
        return repository.getBookmark()
    }
}