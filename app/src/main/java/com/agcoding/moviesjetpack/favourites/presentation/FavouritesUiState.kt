package com.agcoding.moviesjetpack.favourites.presentation

import com.agcoding.moviesjetpack.movies.domain.list.Movie

data class FavouritesUiState(
    val favouriteMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = true
) 