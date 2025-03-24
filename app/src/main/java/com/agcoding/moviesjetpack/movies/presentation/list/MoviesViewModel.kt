package com.agcoding.moviesjetpack.movies.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.domain.list.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesListUiState())
    val state = _state.asStateFlow()

    init {
        _state.value = MoviesListUiState(
            popularMovies = moviesRepository.popularMoviesPagingFlow.filterDuplicates()
                .cachedIn(viewModelScope),
            nowPlayingMovies = moviesRepository.nowPlayingMoviesPagingFlow.filterDuplicates()
                .cachedIn(viewModelScope)
        )
    }

    private fun Flow<PagingData<Movie>>.filterDuplicates(): Flow<PagingData<Movie>> =
        map { pagingData ->
            val seenIds = mutableSetOf<Long>()
            pagingData.filter { movie ->
                when (movie.id) {
                    in seenIds -> false
                    else -> {
                        seenIds.add(movie.id)
                        true
                    }
                }
            }
        }
}
