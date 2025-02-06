package com.agcoding.moviesjetpack.movies.presentation.details

import com.agcoding.moviesjetpack.movies.domain.list.Movie

sealed interface MovieDetailAction {
    data object OnBackLicked : MovieDetailAction
    data object OnFavouriteClicked : MovieDetailAction
    data class OnSelectMovie(val movie: Movie) : MovieDetailAction
}