package com.disha.giphy.domain.usecase

import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.repository.GiphyRepositoryImpl
import com.disha.giphy.data.util.Resource
import com.disha.giphy.domain.repository.GiphyRepository

class GetSearchedGiphyUseCase(
    private val giphyRepository: GiphyRepository
) {

    suspend fun getSearchedGiphy(query: String, offset: Int): Resource<GIF> {
        return giphyRepository.getSearchedGiphy(query, offset)
    }

}