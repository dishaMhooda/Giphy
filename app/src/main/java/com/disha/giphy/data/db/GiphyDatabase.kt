package com.disha.giphy.data.db

import androidx.room.*
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF

@Database(entities = [GIF::class, FavouriteGiphy::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun getGiphyDao(): GiphyDao

}