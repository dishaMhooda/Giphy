package com.disha.giphy.presentation.di

import com.disha.giphy.presentation.ui.adapter.FavouriteGiphyAdapter
import com.disha.giphy.presentation.ui.adapter.TrendingGiphyAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesGiphyAdapter(): TrendingGiphyAdapter {
        return TrendingGiphyAdapter()
    }

    @Singleton
    @Provides
    fun providesFavouriteGiphy(): FavouriteGiphyAdapter {
        return FavouriteGiphyAdapter()
    }
}