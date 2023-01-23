package com.disha.giphy.data.repository.datasourceImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.disha.giphy.data.api.GiphyApiService
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.paging.GiphyPagingSource
import com.disha.giphy.data.repository.datasource.GiphyRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class GiphyRemoteDataSourceImpl @Inject constructor(private val giphyApiService: GiphyApiService) :
    GiphyRemoteDataSource {

    override suspend fun getAllTrendingGif(offset: Int): Response<GIF> {

        /* return Pager(
             config = PagingConfig(
                 pageSize = NETWORK_PAGE_SIZE
             ),
             pagingSourceFactory = { GiphyPagingSource(giphyApiService , offset) }
         ).liveData*/
        return giphyApiService.getTrendingGifs(offset)
    }

    override suspend fun getSearchedGiphy(query: String, offset: Int): Response<GIF> {
        return giphyApiService.getSearchedGiphy(query, offset)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}