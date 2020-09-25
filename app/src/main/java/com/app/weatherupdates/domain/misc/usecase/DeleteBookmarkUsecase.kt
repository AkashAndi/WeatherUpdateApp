package com.app.weatherupdates.domain.misc.usecase

import com.app.weatherupdates.domain.BaseUseCase
import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class DeleteBookmarkUsecase @Inject constructor(
    private val repository: MiscRepository
) : BaseUseCase<Boolean>() {
    override fun buildSingle(data: Map<String, Any?>): Single<Boolean> {
        val id = data[Constants.DELETE_BOOKMARK_DB] as String
        return repository.deleteBookmark(id)
    }
}