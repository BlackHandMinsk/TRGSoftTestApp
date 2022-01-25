package com.example.catsapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.foodrecipesapp.data.database.CatsDao
import com.foodrecipesapp.data.database.entities.CatsEntity
import com.foodrecipesapp.data.database.entities.FavoritesEntity


@Database(
    entities = [CatsEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CatsTypeConverter::class)
abstract class CatsDatabase : RoomDatabase() {


    abstract fun catsDao(): CatsDao
}