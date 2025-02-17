package com.agcoding.moviesjetpack.search.data.datasource.remote

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.search.data.network.SearchMoviesResponse

fun interface SearchRemoteDataSource {

    suspend fun searchMovies(
        query: String,
        page: Int
    ): Result<SearchMoviesResponse, DataError.Remote>
}