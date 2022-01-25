package com.example.catsapi.data.database

import androidx.room.TypeConverter
import com.example.catsapi.model.CatImageModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CatsTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun catsToString(cat: CatImageModel): String {
        return gson.toJson(cat)
    }

    @TypeConverter
    fun stringToCat(data: String): CatImageModel {
        val listType = object : TypeToken<CatImageModel>() {}.type
        return gson.fromJson(data, listType)
    }

}