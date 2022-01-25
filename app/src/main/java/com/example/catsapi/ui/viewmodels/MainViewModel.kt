package com.example.catsapi.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map

import com.example.catsapi.data.Repository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map

import javax.inject.Inject


@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
  private  val repository: Repository
) : ViewModel() {



    fun fetchCatGoImages(): Flow<PagingData<String>> {
        return repository.remote.letCatGoImages()
            .map {
                it.map {cat->
                    cat.url.toString() } }
            .cachedIn(viewModelScope)
    }
}