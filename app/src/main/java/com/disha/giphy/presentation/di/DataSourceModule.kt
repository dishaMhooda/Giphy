package com.disha.giphy.presentation.di

import com.disha.giphy.data.api.GiphyApiService
import com.disha.giphy.data.repository.datasource.GiphyRemoteDataSource
import com.disha.giphy.data.repository.datasourceImpl.GiphyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesGiphyRemoteDataSource(giphyApiService: GiphyApiService): GiphyRemoteDataSource {
        return GiphyRemoteDataSourceImpl(giphyApiService);
    }
}