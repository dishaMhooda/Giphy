package com.disha.giphy.domain.repository

import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GiphyRepository {

    suspend fun getAllTrendingGif(offset: Int): Resource<GIF>
    suspend fun getSearchedGiphy(query: String, offset: Int): Resource<GIF>
    suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy)
    fun getFavouriteGiphy(): Flow<List<FavouriteGiphy>>
    suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy)
}