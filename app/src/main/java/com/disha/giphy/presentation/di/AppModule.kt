package com.disha.giphy.presentation.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.disha.giphy.GifApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("giphyPref", Context.MODE_PRIVATE)
    }
}