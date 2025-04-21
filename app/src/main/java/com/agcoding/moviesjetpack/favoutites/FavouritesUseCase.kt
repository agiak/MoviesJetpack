package com.agcoding.moviesjetpack.favoutites

import com.agcoding.core.storage.FavouriteMovieDB

fun interface FavouritesUseCase {

    suspend operator fun invoke(movie: FavouriteMovieDB)
}