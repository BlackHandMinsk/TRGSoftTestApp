package com.example.catsapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.catsapi.data.Repository
import com.example.catsapi.data.local.entities.FavoritesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class FavoriteCatsViewModel @Inject constructor(private  val repository: Repository
) : ViewModel() {


    fun fetchCatGoImages(): Flow<List<FavoritesEntity>> {
        return repository.local.readFavoriteCats()

    }

      fun deleteAllFavoriteCats() = viewModelScope.launch(Dispatchers.IO) {
        repository.local.deleteAllFavoriteCats()
    }
}