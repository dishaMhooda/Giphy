package com.disha.giphy.presentation.di

import com.disha.giphy.domain.repository.GiphyRepository
import com.disha.giphy.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGiphyUseCase(giphyRepository: GiphyRepository): GetAllTrendingGiphyUseCase {
        return GetAllTrendingGiphyUseCase(giphyRepository)
    }

    @Singleton
    @Provides
    fun providesSaveGiphyAsFavouriteUseCase(giphyRepository: GiphyRepository): SaveGiphyAsFavouriteUseCase {
        return SaveGiphyAsFavouriteUseCase(giphyRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchedGiphyUseCase(giphyRepository: GiphyRepository): GetSearchedGiphyUseCase {
        return GetSearchedGiphyUseCase(giphyRepository)
    }

    @Singleton
    @Provides
    fun providesFavouriteGiphyUseCase(giphyRepository: GiphyRepository): GetFavouriteGiphyUseCase {
        return GetFavouriteGiphyUseCase(giphyRepository)
    }

    @Singleton
    @Provides
    fun providesRemoveGiphyFromFavouriteUseCase(giphyRepository: GiphyRepository): RemoveGiphyFromFavouriteUseCase {
        return RemoveGiphyFromFavouriteUseCase(giphyRepository)
    }
}