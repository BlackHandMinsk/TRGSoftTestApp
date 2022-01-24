package com.example.catsapi.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatImageModel(@Json(name = "id") val id: String?,
                         @Json(name = "url") val url: String?)

