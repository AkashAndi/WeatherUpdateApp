package com.app.weatherupdates.utils

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

fun <T> Single<T>.customSubscribe(success: (T) -> Unit, errorRes: (String?) -> Unit): Disposable {
    return this.subscribe({
        success(it)
    }, {
        errorRes(it.message)
    })
}

fun <T> Flowable<T>.customSubscribe(success: (T) -> Unit, errorRes: (String?) -> Unit): Disposable {
    return this.subscribe({
        success(it)
    }, {
        errorRes(it.message)
    })
}