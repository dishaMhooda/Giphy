package com.disha.giphy.data.repository

import android.util.Log
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.repository.datasource.GiphyLocalDataSource
import com.disha.giphy.data.repository.datasource.GiphyRemoteDataSource
import com.disha.giphy.data.util.Resource
import com.disha.giphy.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GiphyRepositoryImpl @Inject constructor(
    private val giphyRemoteDataSource: GiphyRemoteDataSource,
    private val giphyLocalDataSource: GiphyLocalDataSource
) : GiphyRepository {

    override suspend fun getAllTrendingGif(offset: Int): Resource<GIF> {
        return responseToGiphyResult(giphyRemoteDataSource.getAllTrendingGif(offset))
    }

    override suspend fun getSearchedGiphy(query: String, offset: Int): Resource<GIF> {
        return responseToGiphyResult(giphyRemoteDataSource.getSearchedGiphy(query, offset))
    }

    override suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy) {
        giphyLocalDataSource.saveGiphyAsFavourite(favouriteGiphy)
    }

    override fun getFavouriteGiphy(): Flow<List<FavouriteGiphy>> {
        return giphyLocalDataSource.getSavedGiphyFromDB()
    }

    override suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy) {
        return giphyLocalDataSource.removeGiphyFromFavourite(favouriteGiphy)
    }

    private fun responseToGiphyResult(response: Response<GIF>): Resource<GIF> {
        Log.i("GIP", response.body().toString())
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }


}