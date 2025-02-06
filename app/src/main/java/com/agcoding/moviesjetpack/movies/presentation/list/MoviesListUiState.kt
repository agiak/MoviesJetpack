package com.agcoding.moviesjetpack.movies.presentation.list

import com.agcoding.moviesjetpack.core.presentation.UiText
import com.agcoding.moviesjetpack.movies.domain.list.Movie

data class MoviesListUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
)