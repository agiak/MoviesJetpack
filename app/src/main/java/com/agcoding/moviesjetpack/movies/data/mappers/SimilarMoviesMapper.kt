package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.core.shared.presentation.ext.mapToDate
import com.agcoding.core.shared.presentation.ext.roundToTwoDecimal
import com.agcoding.moviesjetpack.movies.data.network.details.SimilarMovieNetwork
import com.agcoding.moviesjetpack.movies.domain.list.Movie

fun SimilarMovieNetwork.toMovie(): Movie = Movie(
    id = this.id ?: 0L,
    title = title ?: "",
    rating = voteAverage?.roundToTwoDecimal() ?: 0F,
    description = overview ?: "",
    releaseDate = releaseDate?.mapToDate() ?: "",
    imageUrl = URL_POSTER + posterPath,
    isFavourite = false
)