package com.example.catsapi.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catsapi.utils.Constants.Companion.FAVORITE_CATS_TABLE


@Entity(tableName = FAVORITE_CATS_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var url: String
)