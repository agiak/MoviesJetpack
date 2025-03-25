package com.agcoding.moviesjetpack.movies.data.repository.dsetails

import androidx.paging.PagingData
import androidx.paging.map
import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.core.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.favoutites.FavouritesUseCase
import com.agcoding.moviesjetpack.favoutites.IsFavouriteUseCase
import com.agcoding.moviesjetpack.movies.data.datasource.details.MovieDetailsDataSource
import com.agcoding.moviesjetpack.movies.data.datasource.details.ReviewsPagingSource
import com.agcoding.moviesjetpack.movies.data.datasource.details.SimilarMoviesPagingSource
import com.agcoding.moviesjetpack.movies.data.datasource.details.factories.ReviewsPagerFactory
import com.agcoding.moviesjetpack.movies.data.datasource.details.factories.SimilarMoviesPagerFactory
import com.agcoding.moviesjetpack.movies.data.mappers.toCreditsDetails
import com.agcoding.moviesjetpack.movies.data.mappers.toMovie
import com.agcoding.moviesjetpack.movies.data.mappers.toMovieDetails
import com.agcoding.moviesjetpack.movies.data.mappers.toReview
import com.agcoding.moviesjetpack.movies.domain.details.CreditsDetails
import com.agcoding.moviesjetpack.movies.domain.details.MovieDetailsRepository
import com.agcoding.moviesjetpack.movies.domain.details.Review
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val dispatchers: IDispatchers,
    private val moviesDataSource: MovieDetailsDataSource,
    private val isFavouriteUseCase: IsFavouriteUseCase,
    private val favouritesUseCase: FavouritesUseCase,
) : MovieDetailsRepository {

    override var movieID: Long = 0L

    override suspend fun getMovieDetails(id: Long): Result<MovieDetails, DataError.Remote> =
        withContext(dispatchers.backgroundThread()) {
            when (val response = moviesDataSource.getMovieDetails(id)) {
                is Result.Success -> {
                    val isFavourite = isFavouriteUseCase(id)
                    Result.Success(response.data.toMovieDetails(isFavourite))
                }
                else -> {
                    response as Result.Error
                }
            }
        }

    override suspend fun getCredits(id: Long): Result<CreditsDetails, DataError.Remote> =
        withContext(dispatchers.backgroundThread()) {
            when (val response = moviesDataSource.getCredits(id)) {
                is Result.Success -> {
                    Result.Success(response.data.toCreditsDetails())
                }
                else -> response as Result.Error
            }
        }

    override val similarMoviesPagingFlow: Flow<PagingData<Movie>>
        get() = SimilarMoviesPagerFactory.create(
            SimilarMoviesPagingSource(
                moviesId = movieID,
                dataSource = moviesDataSource
            )
        ).flow.map {
            it.map { similarMovieNetwork ->
                similarMovieNetwork.toMovie()
            }
        }

    override val reviewsPagingFlow: Flow<PagingData<Review>>
        get() = ReviewsPagerFactory.create(
            ReviewsPagingSource(
                movieId = movieID,
                dataSource = moviesDataSource
            )
        ).flow.map {
            it.map { reviewNetwork ->
                reviewNetwork.toReview()
            }
        }
}
