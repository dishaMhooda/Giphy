package com.disha.giphy.data.repository.datasourceImpl

import com.disha.giphy.data.db.GiphyDao
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.repository.datasource.GiphyLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GiphyLocalDataSourceImpl @Inject constructor(
    private val giphyDao: GiphyDao
) : GiphyLocalDataSource {
    override suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy) {
        giphyDao.saveGiphyAsFavourite(favouriteGiphy)
    }

    override fun getSavedGiphyFromDB(): Flow<List<FavouriteGiphy>> {
        return giphyDao.getFavouriteGiphy()
    }

    override suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy) {
        giphyDao.removeGiphyFromFavourite(favouriteGiphy)
    }
}