package com.app.weatherupdates.injection.module.builder

import com.app.weatherupdates.ui.BookMarkedLocationFragment
import com.app.weatherupdates.ui.HelpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bookMarkedLocationFragment(): BookMarkedLocationFragment

    @ContributesAndroidInjector
    internal abstract fun helpFragment(): HelpFragment
}