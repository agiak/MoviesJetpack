package com.agcoding.moviesjetpack.favourites.data.repository

import com.agcoding.moviesjetpack.core.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.favourites.data.mappers.toMovie
import com.agcoding.moviesjetpack.favourites.domain.FavouritesRepository
import com.agcoding.moviesjetpack.favoutites.FavouritesUseCase
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.storage.db.FavouriteMovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val favouriteMovieDao: FavouriteMovieDao,
    private val dispatchers: IDispatchers,
    private val favouritesUseCase: FavouritesUseCase
) : FavouritesRepository {

    override fun getFavouriteMovies(): Flow<List<Movie>> =
        favouriteMovieDao.getAllFavoriteMovies()
            .map { movies -> movies.map { it.toMovie() } }
            .flowOn(dispatchers.backgroundThread())

    override suspend fun removeFavourite(movieId: Long) {
        withContext(dispatchers.backgroundThread()) {
            favouriteMovieDao.getMovieById(movieId)?.let { movie ->
                favouritesUseCase(movie)
            }
        }
    }
} 