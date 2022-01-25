package com.example.catsapi.data.local

import com.example.catsapi.data.local.entities.FavoritesEntity
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