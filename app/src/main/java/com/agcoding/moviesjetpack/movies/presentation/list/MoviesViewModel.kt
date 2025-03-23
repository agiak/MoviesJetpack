package com.agcoding.moviesjetpack.movies.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.filter
import com.agcoding.moviesjetpack.movies.domain.list.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesListUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: MoviesUiEvent) {
        when (event) {
            is MoviesUiEvent.OnMovieClicked -> {}
            is MoviesUiEvent.OnFavouriteClicked -> {
                viewModelScope.launch {
                    moviesRepository.onFavouriteChanged(event.movie)
                }
            }
        }
    }

    val moviesPagingFlow = moviesRepository.moviesPagingFlow
        .map { pagingData ->
            val seenIds = mutableSetOf<Long>()
            pagingData
                .filter { movieNetwork ->
                    when (movieNetwork.id) {
                        in seenIds -> false
                        else -> {
                            seenIds.add(movieNetwork.id)
                            true
                        }
                    }
                }
        }
}
