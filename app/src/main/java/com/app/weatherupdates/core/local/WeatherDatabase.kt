package com.app.weatherupdates.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.weatherupdates.BuildConfig
import com.app.weatherupdates.misc.persistance.BookmarkedLocationDao
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation

@Database(
        entities = [BookMarkedLocation::class],
        version = BuildConfig.VERSION_CODE,
        exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun bookmarkedLocationDao(): BookmarkedLocationDao
}
