package com.disha.giphy.data.api

import com.disha.giphy.BuildConfig
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.GIF
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {

    @GET("/v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("offset")
        offset: Int,
        @Query("api_key")
        api_key: String = BuildConfig.API_KEY,
        @Query("limit")
        limit: Int = 20

    ): Response<GIF>

    @GET("/v1/gifs/search")
    suspend fun getSearchedGiphy(
        @Query("q")
        q: String,
        @Query("offset")
        offset: Int,
        @Query("api_key")
        api_key: String = BuildConfig.API_KEY,
        @Query("limit")
        limit: Int = 20

    ): Response<GIF>

    @GET("/v1/gifs/trending")
    suspend fun getTrendingGifsForTesting(
        @Query("offset")
        offset: Int,
        @Query("api_key")
        api_key: String,
        @Query("limit")
        limit: Int = 20

    ): Response<GIF>
}