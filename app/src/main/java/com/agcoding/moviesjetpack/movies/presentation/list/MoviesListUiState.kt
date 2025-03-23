package com.agcoding.moviesjetpack.movies.presentation.list

import androidx.paging.PagingData
import com.agcoding.moviesjetpack.core.presentation.UiText
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesListUiState(
    val isLoading: Boolean = true,
    val error: UiText? = null,
    val popularMovies: Flow<PagingData<Movie>> = emptyFlow(),
    val nowPlayingMovies: Flow<PagingData<Movie>> = emptyFlow()
)