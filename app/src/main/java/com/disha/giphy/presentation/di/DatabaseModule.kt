package com.disha.giphy.presentation.di

import android.app.Application
import androidx.room.Room
import com.disha.giphy.data.db.Converter
import com.disha.giphy.data.db.GiphyDao
import com.disha.giphy.data.db.GiphyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesGiphyDatabase(application: Application): GiphyDatabase {
        return Room.databaseBuilder(application, GiphyDatabase::class.java, "giphy_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converter())
            .build()
    }

    @Singleton
    @Provides
    fun providesGiphyDao(giphyDatabase: GiphyDatabase): GiphyDao {
        return giphyDatabase.getGiphyDao()
    }

}