package com.disha.giphy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.disha.giphy.data.db.Converter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GIF")
@TypeConverters(Converter::class)
data class GIF(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: Pagination
) : java.io.Serializable