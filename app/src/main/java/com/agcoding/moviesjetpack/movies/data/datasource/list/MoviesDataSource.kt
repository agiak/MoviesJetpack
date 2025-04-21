package com.agcoding.moviesjetpack.movies.data.datasource.list

import com.agcoding.core.shared.domain.DataError
import com.agcoding.core.shared.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.MoviesResponse

fun interface MoviesDataSource {

    suspend fun getMovies(page: Int, type: String): Result<MoviesResponse, DataError.Remote>
}