package com.app.weatherupdates.injection.module

import com.app.weatherupdates.data.ApiDataRepository
import com.app.weatherupdates.domain.api.ApiRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class WeatherModule {

    @Singleton
    @Provides
    fun provideAccountRepository(apiDataRepository: ApiDataRepository): ApiRepository =
            apiDataRepository
}