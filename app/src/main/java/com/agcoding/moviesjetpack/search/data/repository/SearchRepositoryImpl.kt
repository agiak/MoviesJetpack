package com.agcoding.moviesjetpack.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork
import com.agcoding.moviesjetpack.search.data.datasource.SearchPagingSource
import com.agcoding.moviesjetpack.search.data.datasource.remote.SearchRemoteDataSource
import com.agcoding.moviesjetpack.search.domain.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
) : SearchRepository {

    override fun searchMovies(query: String): Pager<Int, MovieNetwork> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    searchRemoteDataSource = searchRemoteDataSource,
                    query = query
                )
            }
        )
}