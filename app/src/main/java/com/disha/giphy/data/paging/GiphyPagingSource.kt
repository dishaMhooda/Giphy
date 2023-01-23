package com.disha.giphy.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.disha.giphy.data.api.GiphyApiService
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.util.Resource


private const val STARTING_OFFSET = 1
private const val LIMIT = 20

class GiphyPagingSource(
    private val giphyApiService: GiphyApiService,
    private val offset: Int
) : PagingSource<Int, GIF>() {
    override fun getRefreshKey(state: PagingState<Int, GIF>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GIF> {
        val start = params.key ?: STARTING_OFFSET

        val range = start.until(start + params.loadSize)
        val response = giphyApiService.getTrendingGifs(start)
        val giphyData = response.body()!!
        val convertedData = mutableListOf(giphyData)
        return LoadResult.Page(
            data = convertedData,
            prevKey = params.key?.minus(LIMIT),
            nextKey = params.key?.plus(LIMIT)

        )
    }
}