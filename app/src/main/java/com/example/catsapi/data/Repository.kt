package com.example.catsapi.data

import androidx.paging.ExperimentalPagingApi
import com.example.catsapi.data.database.LocalDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
@ExperimentalPagingApi
class Repository @Inject constructor(
    remoteDataSource: CatImagesRepository,
    localDataSource: LocalDataSource
) {


    val remote = remoteDataSource
    val local = localDataSource
}