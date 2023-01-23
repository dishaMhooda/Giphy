package com.disha.giphy.domain.usecase

import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.domain.repository.GiphyRepository

class RemoveGiphyFromFavouriteUseCase(
    private val giphyRepository: GiphyRepository
) {

    suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy) {
        return giphyRepository.removeGiphyFromFavourite(favouriteGiphy)
    }
}