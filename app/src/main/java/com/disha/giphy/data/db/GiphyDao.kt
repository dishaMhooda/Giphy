package com.disha.giphy.data.db

import androidx.room.*
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(Converter::class)
interface GiphyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy)

    @Query("SELECT * FROM favourite")
    fun getFavouriteGiphy(): Flow<List<FavouriteGiphy>>

    @Delete
    suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy)

}