package com.agcoding.moviesjetpack.favoutites

import com.agcoding.moviesjetpack.core.data.models.FavouriteMovieDB

fun interface FavouritesUseCase {

    suspend operator fun invoke(movie: FavouriteMovieDB)
}