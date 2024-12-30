package com.agcoding.moviesjetpack.movies.presentation

import com.agcoding.moviesjetpack.core.data.Movie
import com.agcoding.moviesjetpack.core.presentation.UiText

data class MoviesListUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)