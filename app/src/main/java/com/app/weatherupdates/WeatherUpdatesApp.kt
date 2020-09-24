package com.app.weatherupdates

import com.app.weatherupdates.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherUpdatesApp : DaggerApplication() {

    companion object {
        lateinit var instance: WeatherUpdatesApp
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}