package com.example.catsapi.data.local


import androidx.room.*
import com.example.catsapi.data.local.entities.FavoritesEntity
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