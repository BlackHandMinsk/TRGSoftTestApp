package com.foodrecipesapp.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.catsapi.model.CatImageModel
import com.foodrecipesapp.data.database.entities.CatsEntity
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CatsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavoriteCat(favoritesEntity: FavoritesEntity)


  @Query("SELECT*FROM favorite_cats_table ORDER BY id ASC")
  fun readFavoriteCats(): Flow<List<FavoritesEntity>>

  @Delete
  suspend fun deleteFavoriteCat(favoritesEntity: FavoritesEntity)

  @Query("DELETE FROM favorite_cats_table")
  suspend fun deleteAllFavoriteCats()

}