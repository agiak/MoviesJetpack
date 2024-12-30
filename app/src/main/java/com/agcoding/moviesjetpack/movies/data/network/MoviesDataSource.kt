package com.agcoding.moviesjetpack.movies.data.network

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result

fun interface MoviesDataSource {

    suspend fun getMovies(): Result<List<MovieNetwork>, DataError.Remote>
}