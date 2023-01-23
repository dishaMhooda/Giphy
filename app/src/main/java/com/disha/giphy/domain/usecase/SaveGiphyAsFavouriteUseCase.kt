package com.disha.giphy.domain.usecase

import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.domain.repository.GiphyRepository

class SaveGiphyAsFavouriteUseCase(
    private val giphyRepository: GiphyRepository
) {

    suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy) =
        giphyRepository.saveGiphyAsFavourite(favouriteGiphy)
}