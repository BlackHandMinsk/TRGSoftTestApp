package com.example.catsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)


data class CatImageModel(@Json(name = "id")
                         val id: String?,
                         @Json(name = "url")
                         val url: String?)

