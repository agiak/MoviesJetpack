package com.agcoding.moviesjetpack.favourites.data.mappers

import com.agcoding.core.storage.FavouriteMovieDB
import com.agcoding.moviesjetpack.movies.domain.list.Movie

fun FavouriteMovieDB.toMovie(): Movie = Movie(
    id = id,
    title = title,
    imageUrl = poster,
    rating = rating,
    description = "",
    releaseDate = releaseDate,
    isFavourite = true
) 