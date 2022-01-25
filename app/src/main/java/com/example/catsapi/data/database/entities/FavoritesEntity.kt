package com.foodrecipesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catsapi.Constants.Companion.FAVORITE_CATS_TABLE
import com.example.catsapi.model.CatImageModel


@Entity(tableName = FAVORITE_CATS_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var url: String
)