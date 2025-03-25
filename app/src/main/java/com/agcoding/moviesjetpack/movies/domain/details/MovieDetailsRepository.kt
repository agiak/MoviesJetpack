package com.agcoding.moviesjetpack.movies.domain.details

import androidx.paging.PagingData
import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    var movieID: Long

    suspend fun getMovieDetails(id: Long): Result<MovieDetails, DataError.Remote>

    suspend fun getCredits(id: Long): Result<CreditsDetails, DataError.Remote>

    val similarMoviesPagingFlow: Flow<PagingData<Movie>>

    val reviewsPagingFlow: Flow<PagingData<Review>>
}