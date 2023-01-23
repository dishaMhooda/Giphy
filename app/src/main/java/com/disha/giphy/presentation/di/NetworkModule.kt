package com.disha.giphy.presentation.di

import com.disha.giphy.BuildConfig
import com.disha.giphy.data.api.GiphyApiService
import com.disha.giphy.data.db.Converter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() : Retrofit{

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
               .baseUrl(BuildConfig.BASE_URL)
               .client(client)
               .addConverterFactory(GsonConverterFactory.create())
               .build()

    }

    @Singleton
    @Provides
    fun provideGifApiService(retrofit: Retrofit):GiphyApiService{
        return retrofit.create(GiphyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGson():Gson = GsonBuilder().create()

}