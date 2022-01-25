package com.example.catsapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.foodrecipesapp.data.database.entities.FavoritesEntity


@Database(
    entities = [FavoritesEntity::class],
    version = 1,
    exportSchema = false
)

abstract class CatsDatabase : RoomDatabase() {


    abstract fun catsDao(): CatsDao
}