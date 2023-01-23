package com.disha.giphy.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.disha.giphy.GifApplication
import com.disha.giphy.data.db.repository.FakeGiphyRepository
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.Images
import com.disha.giphy.data.model.Original
import com.disha.giphy.data.util.SharedPreferences
import com.disha.giphy.domain.usecase.*
import com.disha.giphy.getOrAwaitValue
import com.disha.giphy.presentation.viewmodel.GiphyViewModel
import com.google.common.truth.Truth

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class GiphyViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var giphyViewModel: GiphyViewModel

    @Before
    fun setUp() {
        val sharedPreferences = SharedPreferences(
            ApplicationProvider.getApplicationContext<GifApplication>()
                .getSharedPreferences("giphyPref", Context.MODE_PRIVATE)
        )
        val fakeGiphyRepository = FakeGiphyRepository()
        val getAllTrendingGiphyUseCase = GetAllTrendingGiphyUseCase(fakeGiphyRepository)
        val getFavouriteGiphyUseCase = GetFavouriteGiphyUseCase(fakeGiphyRepository)
        val getSearchedGiphyUseCase = GetSearchedGiphyUseCase(fakeGiphyRepository)
        val removeGiphyFromFavouriteUseCase = RemoveGiphyFromFavouriteUseCase(fakeGiphyRepository)
        val saveGiphyAsFavouriteUseCase = SaveGiphyAsFavouriteUseCase(fakeGiphyRepository)
        giphyViewModel = GiphyViewModel(
            ApplicationProvider.getApplicationContext(),
            getAllTrendingGiphyUseCase,
            saveGiphyAsFavouriteUseCase,
            getSearchedGiphyUseCase,
            getFavouriteGiphyUseCase,
            removeGiphyFromFavouriteUseCase,
            sharedPreferences
        )
    }

    @Test
    fun getFavouriteGiphy_returnCurrentList() {
        val favouriteGiphy = mutableListOf<FavouriteGiphy>()
        favouriteGiphy.add(
            FavouriteGiphy(
                "1",
                Images(Original(url = "https://media2.giphy.com/media/w6fWo7nFQ9slK5D6IB/giphy.gif?cid=50c270aao9yhoshkabvwqb2ux98kt787ddl05ndu9pcfbbum&rid=giphy.gif&ct=g")),
                "New Year Asian GIF by Hello All",
                "2023-01-22 03:00:07",
                "gif",
                "https://giphy.com/gifs/helloall-new-year-chinese-2023-w6fWo7nFQ9slK5D6IB",
                "helloall"
            )
        )

        val currentList = giphyViewModel.getFavouriteGiphy().getOrAwaitValue()
        Truth.assertThat(currentList).isEqualTo(favouriteGiphy)
    }


}