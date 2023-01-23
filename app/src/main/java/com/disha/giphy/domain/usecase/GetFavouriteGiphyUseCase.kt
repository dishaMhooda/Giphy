package com.disha.giphy.domain.usecase

import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow


class GetFavouriteGiphyUseCase(private val giphyRepository: GiphyRepository) {

    fun getFavouriteGiphy(): Flow<List<FavouriteGiphy>> {
        return giphyRepository.getFavouriteGiphy()
    }
}