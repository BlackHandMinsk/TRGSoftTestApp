package com.example.catsapi.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.catsapi.model.CatImageModel
import com.example.catsapi.repository.remote.CatsApiService
import com.example.catsapi.repository.remote.RemoteInjector
import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Constructor
import javax.inject.Inject


@ExperimentalPagingApi
class CatImagesRepository @Inject constructor(
   private val catsApiService: CatsApiService,
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10


     //   fun getInstance() = CatImagesRepository()
    }



    fun letCatGoImages(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<CatImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatGoImagePagingSource(catsApiService) }
        ).flow
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}