package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.moviesjetpack.core.presentation.ext.mapToDate
import com.agcoding.moviesjetpack.core.presentation.ext.roundToTwoDecimal
import com.agcoding.moviesjetpack.movies.data.network.details.MovieDetailsResponse
import com.agcoding.moviesjetpack.movies.presentation.details.MovieDetails

fun MovieDetailsResponse.toMovieDetails(isFavourite: Boolean = false): MovieDetails =
    MovieDetails(
        id = id ?: 0L,
        title = title ?: "",
        description = overview ?: "",
        imageUrl = URL_POSTER + posterPath,
        releaseDate = releaseDate?.mapToDate() ?: "",
        isFavourite = isFavourite,
        type = getType(),
        rating = (rating?.div(2.0F))?.roundToTwoDecimal() ?: 0F
    )

fun MovieDetailsResponse.getType(): String = genres?.joinToString(", ") { it.name.toString() } ?: ""