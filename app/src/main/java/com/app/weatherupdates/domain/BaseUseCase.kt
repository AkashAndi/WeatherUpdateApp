package com.app.weatherupdates.domain

import io.reactivex.Single

abstract class BaseUseCase<T> {

    abstract fun buildSingle(data: Map<String, Any?> = emptyMap()): Single<T>

    fun execute(data: Map<String, Any?> = emptyMap()): Single<T> {
        return buildSingle(data).compose(SchedulerTransformer())
    }
}
