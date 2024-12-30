package com.agcoding.moviesjetpack.movies.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MoviesViewModel @Inject constructor(

) {

    private val _state = MutableStateFlow(MoviesListUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: MoviesUiEvent) {
        when(event) {
            is MoviesUiEvent.OnMovieClicked -> {}
            MoviesUiEvent.OnSearchClicked -> {}
        }
    }
}