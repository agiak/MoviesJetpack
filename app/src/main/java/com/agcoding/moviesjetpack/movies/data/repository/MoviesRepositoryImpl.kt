package com.agcoding.moviesjetpack.movies.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.core.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.favoutites.FavouritesUseCase
import com.agcoding.moviesjetpack.favoutites.IsFavouriteUseCase
import com.agcoding.moviesjetpack.movies.data.datasource.details.factories.MoviesPagerFactory
import com.agcoding.moviesjetpack.movies.data.datasource.list.MoviesDataSource
import com.agcoding.moviesjetpack.movies.data.datasource.list.MoviesPagingSource
import com.agcoding.moviesjetpack.movies.data.mappers.toFavouriteMovieDB
import com.agcoding.moviesjetpack.movies.data.mappers.toMovie
import com.agcoding.moviesjetpack.movies.data.network.MoviesResponse
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.domain.list.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val popularMoviesPagingSource: MoviesPagingSource,
    private val nowPlayingMoviesPagingSource: MoviesPagingSource,
    private val favouritesUseCase: FavouritesUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase,
    private val dispatchers: IDispatchers
) : MoviesRepository {

    override suspend fun fetchMovies(
        page: Int,
        type: String
    ): Result<MoviesResponse, DataError.Remote> =
        moviesDataSource.getMovies(page, type)

    override val popularMoviesPagingFlow: Flow<PagingData<Movie>>
        get() = MoviesPagerFactory.create(popularMoviesPagingSource).flow.map {
            it.map { movieNetwork ->
                movieNetwork.toMovie().apply { isFavourite = isFavouriteUseCase(this.id) }
            }
        }

    override val nowPlayingMoviesPagingFlow: Flow<PagingData<Movie>>
        get() = MoviesPagerFactory.create(nowPlayingMoviesPagingSource).flow.map {
            it.map { movieNetwork ->
                movieNetwork.toMovie().apply { isFavourite = isFavouriteUseCase(this.id) }
            }
        }

    override suspend fun onFavouriteChanged(movie: Movie) =
        withContext(dispatchers.backgroundThread()) {
            favouritesUseCase(movie.toFavouriteMovieDB())
        }
}

