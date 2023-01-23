package com.disha.giphy.data.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("response_id")
    val response_id: String,
    @SerializedName("status")
    val status: Int
)