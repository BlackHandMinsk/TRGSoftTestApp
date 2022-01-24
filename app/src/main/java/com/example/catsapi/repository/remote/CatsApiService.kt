package com.example.catsapi.repository.remote

import com.example.catsapi.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApiService {
    @GET("/v1/images/search")
    suspend fun getCatsImages(@Query("page") page: Int, @Query("limit") size: Int): List<CatImageModel>
}