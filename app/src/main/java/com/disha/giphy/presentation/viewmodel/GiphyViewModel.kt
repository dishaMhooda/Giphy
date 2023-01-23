package com.disha.giphy.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.disha.giphy.data.model.FavouriteGiphy
import com.disha.giphy.data.model.GIF
import com.disha.giphy.data.util.Network.isNetworkAvailable
import com.disha.giphy.data.util.Resource
import com.disha.giphy.data.util.SharedPreferences
import com.disha.giphy.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val application: Application,
    private val getAllTrendingGiphyUseCase: GetAllTrendingGiphyUseCase,
    private val saveGiphyAsFavouriteUseCase: SaveGiphyAsFavouriteUseCase,
    private val getSearchedGiphyUseCase: GetSearchedGiphyUseCase,
    private val getFavouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val removeGiphyFromFavouriteUseCase: RemoveGiphyFromFavouriteUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val giphy: MutableLiveData<Resource<GIF>> = MutableLiveData()

    val favourite: MutableLiveData<Resource<FavouriteGiphy>> = MutableLiveData()

    fun getAllTrendingGif(offset: Int) = viewModelScope.launch(IO) {
        giphy.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getAllTrendingGiphyUseCase.getAllTrendingGif(offset)
                giphy.postValue((apiResult))
            }
        } catch (e: Exception) {

        }
    }

    fun getSearchedGiphy(query: String, offset: Int) = viewModelScope.launch(IO) {
        giphy.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getSearchedGiphyUseCase.getSearchedGiphy(query, offset)
                giphy.postValue((apiResult))
            }
        } catch (e: Exception) {

        }
    }

    fun saveGiphyAsFavourite(favouriteGiphy: FavouriteGiphy) =
        viewModelScope.launch { saveGiphyAsFavouriteUseCase.saveGiphyAsFavourite(favouriteGiphy) }

    fun getFavouriteGiphy() = liveData {
        getFavouriteGiphyUseCase.getFavouriteGiphy().collect {
            emit(it)
        }
    }

    fun removeGiphyFromFavourite(favouriteGiphy: FavouriteGiphy) =
        viewModelScope.launch {
            removeGiphyFromFavouriteUseCase.removeGiphyFromFavourite(
                favouriteGiphy
            )
        }

    fun getSearchedHistory(): List<String> {
        return sharedPreferences.getSearchedKeyword()
    }

    fun saveSearchedKeyword(keyword: String) {
        Log.i("Saved KeyWord", keyword)
        sharedPreferences.saveSearchedkeyword(keyword)
    }

    fun getApplicationContext(): Application {
        return application
    }

}