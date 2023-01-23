package com.disha.giphy.data.db.repository

import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.model.Images
import com.disha.giphy.data.model.Original
import com.disha.giphy.data.util.Resource
import com.disha.giphy.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeGiphyRepository : GiphyRepository {

    private val favouriteGiphy = FavouriteGiphy(
        "1",
        Images(Original(url = "https://media2.giphy.com/media/w6fWo7nFQ9slK5D6IB/giphy.gif?cid=50c270aao9yhoshkabvwqb2ux98kt787ddl05ndu9pcfbbum&rid=giphy.gif&ct=g")),
        "New Year Asian GIF by Hello All",
        "2023-01-22 03:00:07",
        "gif",
        "https://giphy.com/gifs/helloall-new-year-chinese-2023-w6fWo7nFQ9slK5D6IB",
        "helloall"
    )

    override suspend fun getAllTrendingGif(offset: Int): Resource<GIF> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedGiphy(query: String, offset: Int): Resource<GIF> {
        TODO("Not yet implemented")
    }

    override suspend fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy) {
        TODO("Not yet implemented")
    }

    override fun getFavouriteGiphy(): Flow<List<FavouriteGiphy>> {
        return flowOf(mutableListOf(favouriteGiphy))
    }

    override suspend fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy) {
        TODO("Not yet implemented")
    }
}