package com.disha.giphy.data.repository.datasource

import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.GIF
import retrofit2.Response

interface GiphyRemoteDataSource {

    suspend fun getAllTrendingGif(offset: Int): Response<GIF>
    suspend fun getSearchedGiphy(query: String, offset: Int): Response<GIF>

}