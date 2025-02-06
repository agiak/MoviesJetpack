package com.agcoding.moviesjetpack.movies.presentation.details

import com.agcoding.moviesjetpack.core.presentation.UiText
import com.agcoding.moviesjetpack.movies.domain.details.CreditsDetails
import com.agcoding.moviesjetpack.movies.domain.list.Movie

data class MovieDetailsState(
    val details: MovieDetails = MovieDetails(),
    val credits: CreditsDetails = CreditsDetails(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

data class MovieDetails(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val isFavourite: Boolean = false,
    val releaseDate: String = "",
    val type: String = "",
    val rating: Float = 0F,
)

data class SimilarMoviesState(
    val movies: List<Movie>,
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

data class CastState(
    val cast: List<String>,
    val isLoading: Boolean = true,
    val error: UiText? = null,
)

data class ReviewsState(
    val reviews: List<String>,
    val isLoading: Boolean = true,
    val error: UiText? = null,
)