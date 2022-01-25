package com.example.catsapi.di

import android.content.Context
import androidx.room.Room
import com.example.catsapi.utils.Constants.Companion.DATABASE_NAME
import com.example.catsapi.data.local.CatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CatsDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: CatsDatabase) = database.catsDao()
}