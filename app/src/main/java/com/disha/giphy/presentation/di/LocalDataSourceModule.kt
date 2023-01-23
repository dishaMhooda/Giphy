package com.disha.giphy.presentation.di

import com.disha.giphy.data.db.GiphyDao
import com.disha.giphy.data.repository.datasource.GiphyLocalDataSource
import com.disha.giphy.data.repository.datasourceImpl.GiphyLocalDataSourceImpl
import com.disha.giphy.domain.repository.GiphyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(giphyDao: GiphyDao): GiphyLocalDataSource {
        return GiphyLocalDataSourceImpl(giphyDao)
    }
}