package com.disha.giphy.presentation.di

import com.disha.giphy.data.repository.GiphyRepositoryImpl
import com.disha.giphy.data.repository.datasource.GiphyLocalDataSource
import com.disha.giphy.data.repository.datasource.GiphyRemoteDataSource
import com.disha.giphy.domain.repository.GiphyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesGiphyRepository(
        giphyRemoteDataSource: GiphyRemoteDataSource,
        giphyLocalDataSource: GiphyLocalDataSource
    ): GiphyRepository {
        return GiphyRepositoryImpl(giphyRemoteDataSource, giphyLocalDataSource)
    }

}