package com.disha.giphy.data.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("original")
    val original: Original,
)