package com.app.weatherupdates.domain.misc.usecase

import com.app.weatherupdates.domain.BaseUseCase
import com.app.weatherupdates.domain.misc.MiscRepository
import io.reactivex.Single
import javax.inject.Inject

class DeleteAllUsecase @Inject constructor(
        private val repository: MiscRepository
) : BaseUseCase<Boolean>() {
    override fun buildSingle(data: Map<String, Any?>): Single<Boolean> {
        return repository.deleteAll()
    }
}