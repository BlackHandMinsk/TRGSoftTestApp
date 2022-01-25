package com.example.catsapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.catsapi.data.Repository
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CatInfoViewModel
@Inject constructor(
    private  val repository: Repository
) : ViewModel(){


    fun readFavoritesCats():Flow<List<FavoritesEntity>> {
      return  repository.local.readFavoriteCats()
    }


    fun addToFavorite(favoritesEntity: FavoritesEntity){
        viewModelScope.launch (Dispatchers.IO){
            repository.local.insertFavoriteCats(favoritesEntity)
        }
    }


    fun deleteFavoriteCats(favoritesEntity: FavoritesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteCats(favoritesEntity)
        }
}