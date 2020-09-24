package com.app.weatherupdates.injection.module

import com.app.weatherupdates.injection.scope.ActivityScoped
import com.app.weatherupdates.injection.module.builder.WeatherFragmentBuilderModule
import com.app.weatherupdates.ui.PickLocationActivity
import com.app.weatherupdates.ui.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [WeatherFragmentBuilderModule::class])
    internal abstract fun weatherActivity(): WeatherActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun pickLocationActivity(): PickLocationActivity
}
