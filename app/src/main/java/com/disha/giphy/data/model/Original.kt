package com.disha.giphy.data.model

import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("url")
    val url: String,
)