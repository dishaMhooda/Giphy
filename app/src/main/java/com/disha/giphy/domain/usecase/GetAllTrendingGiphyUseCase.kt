package com.disha.giphy.domain.usecase

import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.util.Resource
import com.disha.giphy.domain.repository.GiphyRepository
import retrofit2.Response

class GetAllTrendingGiphyUseCase(
    private val giphyRepository: GiphyRepository
) {

    suspend fun getAllTrendingGif(offset: Int): Resource<GIF> {
        return giphyRepository.getAllTrendingGif(offset)
    }
}