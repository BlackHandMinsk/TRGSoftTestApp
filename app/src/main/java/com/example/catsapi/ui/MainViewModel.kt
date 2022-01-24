package com.example.catsapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.catsapi.data.CatImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
  private  val repository: CatImagesRepository
) : ViewModel() {

    fun fetchCatGoImages(): Flow<PagingData<String>> {
        return repository.letCatGoImages()
            .map { it.map { it.url.toString() } }
            .cachedIn(viewModelScope)
    }
}