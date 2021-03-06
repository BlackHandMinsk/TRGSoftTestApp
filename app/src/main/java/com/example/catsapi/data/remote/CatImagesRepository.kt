package com.example.catsapi.data.remote

import androidx.paging.*
import com.example.catsapi.model.CatImageModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class CatImagesRepository @Inject constructor(
    private val catsApiService: CatsApiService,
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10

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