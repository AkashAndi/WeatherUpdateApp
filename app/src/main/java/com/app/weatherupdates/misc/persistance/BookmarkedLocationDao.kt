package com.app.weatherupdates.misc.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import io.reactivex.Single

@Dao
interface BookmarkedLocationDao {

    @Insert
    fun insertItem(bookMarkedLocation: BookMarkedLocation): Single<Long>

    @Query("DELETE FROM " + BookMarkedLocation.TABLE_NAME + " where " + BookMarkedLocation.TIMESTAMP + "= :id")
    fun deleteLocation(id: String)

    @Query("SELECT * FROM " + BookMarkedLocation.TABLE_NAME + " ORDER BY " + BookMarkedLocation.TIMESTAMP + " DESC LIMIT 10")
    fun getLocatoinDescending(): Single<List<BookMarkedLocation>>

}