package com.example.catsapi.data.database

import com.foodrecipesapp.data.database.CatsDao
import com.foodrecipesapp.data.database.entities.CatsEntity
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val catsDao: CatsDao
) {


     fun readFavoriteCats(): Flow<List<FavoritesEntity>> {
        return catsDao.readFavoriteCats()
    }


    suspend fun insertFavoriteCats(favoritesEntity: FavoritesEntity) {
        catsDao.insertFavoriteCat(favoritesEntity)
    }


    suspend fun deleteFavoriteCats(favoritesEntity: FavoritesEntity) {
        catsDao.deleteFavoriteCat(favoritesEntity)
    }


    suspend fun deleteAllFavoriteCats() {
        catsDao.deleteAllFavoriteCats()
    }
}