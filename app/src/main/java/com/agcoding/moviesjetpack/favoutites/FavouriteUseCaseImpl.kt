package com.agcoding.moviesjetpack.favoutites

import com.agcoding.core.shared.domain.dispatchers.IDispatchers
import com.agcoding.core.storage.FavouriteMovieDB
import com.agcoding.core.storage.FavouriteMovieDao
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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