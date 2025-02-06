package com.agcoding.moviesjetpack.favoutites

import com.agcoding.moviesjetpack.core.data.models.FavouriteMovieDB
import com.agcoding.moviesjetpack.core.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.storage.db.FavouriteMovieDao
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteUseCaseImpl @Inject constructor(
    private val dispatchers: IDispatchers,
    private val localDao: FavouriteMovieDao,
    private val isMovieFavourite: IsFavouriteUseCase,
) : FavouritesUseCase {
    override suspend fun invoke(movie: FavouriteMovieDB) {
        withContext(dispatchers.backgroundThread()) {
            if (isMovieFavourite(movie.id).not())
                localDao.insertFavoriteMovie(movie)
            else
                localDao.deleteFavoriteMovie(movie)
        }
    }
}