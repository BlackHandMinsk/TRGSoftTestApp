package com.example.catsapi.di

import com.example.catsapi.utils.Constants.Companion.API_KEY
import com.example.catsapi.utils.Constants.Companion.BASE_URL
import com.example.catsapi.data.remote.CatsApiService
import com.example.catsapi.utils.Constants.Companion.HEADER_API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        okHttpNetworkInterceptor: Interceptor,
        httpLogger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpNetworkInterceptor)
            .addInterceptor(httpLogger)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CatsApiService {
        return retrofit.create(CatsApiService::class.java)
    }


    @Singleton
    @Provides
    fun getOkHttpNetworkInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest =
                    chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
                return chain.proceed(newRequest)
            }
        }
    }

    @Singleton
    @Provides
    fun getHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}