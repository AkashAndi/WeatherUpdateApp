package com.app.weatherupdates.misc.persistance.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookMarkedLocation(
        @ColumnInfo(name = LATITUDE)
        var latitude: Double? = 0.0,
        @ColumnInfo(name = LONGITUDE)
        var longitude: Double? = 0.0,
        @ColumnInfo(name = ADDRESS)
        var address: String? = null ?: "",
        @PrimaryKey @ColumnInfo(name = TIMESTAMP) @NonNull var timeStamp: String = ""
) {

    companion object {
        const val TABLE_NAME = "bookmarked_location"
        const val ADDRESS = "address"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val TIMESTAMP = "timestamp"
    }
}