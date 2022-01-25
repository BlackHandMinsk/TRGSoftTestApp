package com.example.catsapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.catsapi.data.Repository
import com.example.catsapi.model.CatImageModel
import com.foodrecipesapp.data.database.entities.FavoritesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class FavoriteCatsViewModel @Inject constructor(private  val repository: Repository
) : ViewModel() {


    fun fetchCatGoImages(): Flow<List<FavoritesEntity>> {
        return repository.local.readFavoriteCats()

    }
}