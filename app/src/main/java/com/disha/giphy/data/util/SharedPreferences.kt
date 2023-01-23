package com.disha.giphy.data.util

import android.content.SharedPreferences
import javax.inject.Inject

open class SharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private var historyList = HashSet<String>()

    fun saveSearchedkeyword(keyword: String) {
        historyList.add(keyword)
        sharedPreferences.edit().putStringSet("SEARCHED_KEYWORD", historyList)
    }

    fun getSearchedKeyword(): List<String> {
        val list =
            ArrayList<String>(sharedPreferences.getStringSet("SEARCHED_KEYWORD", historyList))
        return list
    }
}