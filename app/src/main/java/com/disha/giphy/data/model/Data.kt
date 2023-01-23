package com.disha.giphy.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Data(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("title")
    val title: String,
    @SerializedName("trending_datetime")
    val trending_datetime: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("username")
    val username: String
)