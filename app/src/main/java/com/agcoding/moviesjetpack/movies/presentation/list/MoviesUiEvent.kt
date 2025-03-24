package com.agcoding.moviesjetpack.movies.presentation.list

sealed interface MoviesUiEvent {
    data class OnMovieClicked(val movieID: Long) : MoviesUiEvent
}