package com.foodrecipesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catsapi.Constants.Companion.CATS_TABLE
import com.example.catsapi.model.CatImageModel


@Entity(tableName = CATS_TABLE)
class CatsEntity(
    var cat: CatImageModel
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}