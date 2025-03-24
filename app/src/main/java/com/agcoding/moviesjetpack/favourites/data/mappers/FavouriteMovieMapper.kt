package com.agcoding.moviesjetpack.favourites.data.mappers

import com.agcoding.moviesjetpack.core.data.models.FavouriteMovieDB
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