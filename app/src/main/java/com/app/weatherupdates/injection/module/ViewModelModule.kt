package com.app.weatherupdates.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.base.ViewModelFactory
import com.app.weatherupdates.injection.ViewModelKey
import com.app.weatherupdates.viewmodel.WeatherDetailsViewModel
import com.app.weatherupdates.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDetailsViewModel::class)
    fun bindWeatherDetailsViewModel(weatherDetailsViewModel: WeatherDetailsViewModel): ViewModel
}
