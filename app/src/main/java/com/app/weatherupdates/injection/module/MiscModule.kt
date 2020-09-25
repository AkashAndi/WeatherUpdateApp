package com.app.weatherupdates.injection.module

import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.misc.MiscDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class MiscModule {

    @Singleton
    @Provides
    fun provideMiscRepository(miscDataRepository: MiscDataRepository): MiscRepository =
            miscDataRepository


}