package com.example.catsapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catsapi.data.local.entities.FavoritesEntity


@Database(
    entities = [FavoritesEntity::class],
    version = 1,
    exportSchema = false
)

abstract class CatsDatabase : RoomDatabase() {


    abstract fun catsDao(): CatsDao
}