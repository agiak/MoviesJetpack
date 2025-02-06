package com.agcoding.moviesjetpack.favoutites

import com.agcoding.moviesjetpack.core.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.storage.db.FavouriteMovieDao
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsFavouriteUseCaseImpl @Inject constructor(
    private val dispatchers: IDispatchers,
    private val localDao: FavouriteMovieDao,
) : IsFavouriteUseCase {
    override suspend fun invoke(movieId: Long): Boolean =
        withContext(dispatchers.backgroundThread()) {
            localDao.isMovieFavorite(movieId)
        }
}