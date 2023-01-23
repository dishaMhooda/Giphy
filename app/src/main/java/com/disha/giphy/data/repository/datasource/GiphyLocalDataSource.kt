package com.disha.giphy.data.repository.datasource

import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import kotlinx.coroutines.flow.Flow


interface GiphyLocalDataSource {

    suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy)
    fun getSavedGiphyFromDB(): Flow<List<FavouriteGiphy>>
    suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy)
}