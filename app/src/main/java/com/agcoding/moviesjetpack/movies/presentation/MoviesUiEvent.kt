package com.agcoding.moviesjetpack.movies.presentation

sealed interface MoviesUiEvent {

    data class OnMovieClicked(val movieID: Long) : MoviesUiEvent

    data object OnSearchClicked : MoviesUiEvent
}