package com.agcoding.moviesjetpack.movies.data.datasource.details

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.details.CreditsResponse
import com.agcoding.moviesjetpack.movies.data.network.details.MovieDetailsResponse
import com.agcoding.moviesjetpack.movies.data.network.details.SimilarResponse

interface MovieDetailsDataSource {

    suspend fun getMovieDetails(id: Long): Result<MovieDetailsResponse, DataError.Remote>

    suspend fun getCredits(id: Long): Result<CreditsResponse, DataError.Remote>

    suspend fun getSimilarMovies(id: Long, page: Int): Result<SimilarResponse, DataError.Remote>
}