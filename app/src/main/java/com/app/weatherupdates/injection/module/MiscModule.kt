package com.app.weatherupdates.injection.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.weatherupdates.core.local.WeatherDatabase
import com.app.weatherupdates.domain.misc.MiscRepository
import com.app.weatherupdates.misc.MiscDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MiscModule {

    @Singleton
    @Provides
    fun provideMiscRepository(miscDataRepository: MiscDataRepository): MiscRepository =
            miscDataRepository

    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "weather_db")
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .fallbackToDestructiveMigration()
                .build()
    }

}