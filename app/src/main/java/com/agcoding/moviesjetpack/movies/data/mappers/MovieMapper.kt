package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.moviesjetpack.core.presentation.ext.mapToDate
import com.agcoding.moviesjetpack.core.presentation.ext.roundToTwoDecimal
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork
import com.agcoding.moviesjetpack.movies.domain.list.Movie

const val URL_POSTER = "http://image.tmdb.org/t/p/w500"

fun MovieNetwork.toMovie(): Movie = Movie(
    id = this.id ?: 0L,
    title = title ?: "",
    rating = voteAverage?.roundToTwoDecimal() ?: 0F,
    description = overview ?: "",
    releaseDate = releaseDate?.mapToDate() ?: "",
    imageUrl = URL_POSTER + posterPath,
    isFavourite = false
)