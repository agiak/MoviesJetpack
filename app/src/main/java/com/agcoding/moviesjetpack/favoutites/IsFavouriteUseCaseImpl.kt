package com.agcoding.moviesjetpack.favoutites

import com.agcoding.core.shared.domain.dispatchers.IDispatchers
import com.agcoding.core.storage.FavouriteMovieDao
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsFavouriteUseCaseImpl @Inject constructor(
    private val dispatchers: IDispatchers,
    private val localDao: FavouriteMovieDao,
) : IsFavouriteUseCase {

    override suspend fun invoke(movieId: Long): Boolean =
        withContext(dispatchers.backgroundThread()) {
            localDao.isMovieFavorite(movieId)
        }
}