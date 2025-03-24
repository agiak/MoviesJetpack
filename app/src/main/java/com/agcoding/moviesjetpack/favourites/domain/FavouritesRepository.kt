package com.agcoding.moviesjetpack.favourites.domain

import com.agcoding.moviesjetpack.movies.domain.list.Movie
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun getFavouriteMovies(): Flow<List<Movie>>
    suspend fun removeFavourite(movieId: Long)
} 