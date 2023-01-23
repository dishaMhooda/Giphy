package com.disha.giphy.presentation.di

import android.app.Application
import com.disha.giphy.GifApplication
import com.disha.giphy.data.util.SharedPreferences
import com.disha.giphy.domain.usecase.*
import com.disha.giphy.presentation.viewmodel.GiphyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Singleton
    @Provides
    fun providesGifTrendingViewModel(
        application: Application,
        giphyUseCase: GetAllTrendingGiphyUseCase,
        giphySaveGiphyAsFavouriteUseCase: SaveGiphyAsFavouriteUseCase,
        giphySearchedGiphyUseCase: GetSearchedGiphyUseCase,
        getFavouriteGiphyUseCase: GetFavouriteGiphyUseCase,
        removeGiphyFromFavouriteUseCase: RemoveGiphyFromFavouriteUseCase,
        sharedPreferences: SharedPreferences
    )
            : GiphyViewModel {
        return GiphyViewModel(
            application,
            giphyUseCase,
            giphySaveGiphyAsFavouriteUseCase,
            giphySearchedGiphyUseCase,
            getFavouriteGiphyUseCase,
            removeGiphyFromFavouriteUseCase,
            sharedPreferences
        )
    }


}