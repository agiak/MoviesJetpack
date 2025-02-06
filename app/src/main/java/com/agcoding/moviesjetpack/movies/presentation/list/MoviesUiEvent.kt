package com.agcoding.moviesjetpack.movies.presentation.list

import com.agcoding.moviesjetpack.movies.domain.list.Movie

sealed interface MoviesUiEvent {

    data class OnMovieClicked(val movieID: Long) : MoviesUiEvent

    data object OnSearchClicked : MoviesUiEvent

    data class OnFavouriteClicked(val movie: Movie) : MoviesUiEvent
}