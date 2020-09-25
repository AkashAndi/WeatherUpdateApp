package com.app.weatherupdates.misc.persistance.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookMarkedLocation(
        @ColumnInfo(name = LATITUDE)
        var lat: Double? = 0.toDouble(),
        @ColumnInfo(name = LONGITUDE)
        var lon: Double? = 0.toDouble(),
        @ColumnInfo(name = ADDRESS)
        var address: String? = null ?: "",
        @ColumnInfo(name = TIMESTAMP)
        var timeStamp: Long = 0
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Long = 0

    companion object {
        const val TABLE_NAME = "bookmarked_location"
        const val ADDRESS = "address"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val ID = "id"
        const val TIMESTAMP = "timestamp"
    }
}