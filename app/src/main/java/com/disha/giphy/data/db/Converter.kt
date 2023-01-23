package com.disha.giphy.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.disha.giphy.data.model.Data
import com.disha.giphy.data.model.Images
import com.disha.giphy.data.model.Meta
import com.disha.giphy.data.model.Pagination
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@ProvidedTypeConverter
class Converter {

    @TypeConverter
    fun fromData(data: String): List<Data> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun toData(json: List<Data>): String {
        var gson = Gson()
        return gson.toJson(json)
    }

    @TypeConverter
    fun fromImage(images: Images): String {
        val gson = Gson()
        return gson.toJson(images)
    }

    @TypeConverter
    fun toImage(json: String): Images {
        return Gson().fromJson(json, Images::class.java)
    }

    @TypeConverter
    fun fromMeta(meta: Meta): String {
        val gson = Gson()
        return gson.toJson(meta)
    }

    @TypeConverter
    fun toMeta(json: String): Meta {
        return Gson().fromJson(json, Meta::class.java)
    }

    @TypeConverter
    fun fromPagination(pagination: Pagination): String {
        val gson = Gson()
        return gson.toJson(pagination)
    }

    @TypeConverter
    fun toPagination(json: String): Pagination {
        return Gson().fromJson(json, Pagination::class.java)
    }

}