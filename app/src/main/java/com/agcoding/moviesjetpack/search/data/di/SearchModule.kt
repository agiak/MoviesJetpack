package com.agcoding.moviesjetpack.search.data.di

import com.agcoding.moviesjetpack.search.data.datasource.remote.SearchRemoteDataSource
import com.agcoding.moviesjetpack.search.data.datasource.remote.SearchRemoteDataSourceImpl
import com.agcoding.moviesjetpack.search.data.repository.SearchRepositoryImpl
import com.agcoding.moviesjetpack.search.domain.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindSearchRemoteDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl
    ): SearchRemoteDataSource

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
