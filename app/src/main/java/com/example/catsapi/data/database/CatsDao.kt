package com.example.catsapi.data.database


import androidx.room.*
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