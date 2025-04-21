package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.core.storage.FavouriteMovieDB
import com.agcoding.moviesjetpack.movies.domain.list.Movie

fun Movie.toFavouriteMovieDB(): FavouriteMovieDB = FavouriteMovieDB(
    id = this.id,
    title = this.title,
    poster = this.imageUrl,
    rating = this.rating,
    releaseDate = this.releaseDate
)