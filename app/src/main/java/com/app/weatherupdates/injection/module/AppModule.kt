/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.weatherupdates.injection.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.weatherupdates.R
import com.app.weatherupdates.WeatherUpdatesApp
import com.app.weatherupdates.core.local.AppPrefs
import com.app.weatherupdates.core.local.WeatherDatabase
import com.app.weatherupdates.core.network.NetworkAvailabilityInterceptor
import com.app.weatherupdates.core.network.NetworkErrorIntercepter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: WeatherUpdatesApp): Application = app

    @Singleton
    @Provides
    fun provideConnectivityManager(app: Application): ConnectivityManager {
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideNetworkAvailabilityInterceptor(
            app: Application,
            connectivityManager: ConnectivityManager) = NetworkAvailabilityInterceptor(
            connectivityManager,
            app.getString(R.string.error_no_network))


    @Singleton
    @Provides
    fun provideAppPrefs(app: Application): AppPrefs {
        return AppPrefs(app)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideNetworkErrorInterceptor(app: Application, gson: Gson): NetworkErrorIntercepter =
            NetworkErrorIntercepter(app, gson)


    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "weather_db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .fallbackToDestructiveMigration()
            .build()
    }
}
