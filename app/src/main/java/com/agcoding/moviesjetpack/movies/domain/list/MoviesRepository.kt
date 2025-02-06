package com.agcoding.moviesjetpack.movies.domain.list

import androidx.paging.PagingData
import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchMovies(
        page: Int,
        type: String
    ): Result<MoviesResponse, DataError.Remote>

    val moviesPagingFlow: Flow<PagingData<Movie>>

    suspend fun onFavouriteChanged(movie: Movie)
}