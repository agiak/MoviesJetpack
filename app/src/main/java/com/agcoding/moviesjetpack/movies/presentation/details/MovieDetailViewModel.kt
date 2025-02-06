package com.agcoding.moviesjetpack.movies.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.agcoding.moviesjetpack.app.Route
import com.agcoding.moviesjetpack.core.data.extensions.toUiText
import com.agcoding.moviesjetpack.core.domain.onError
import com.agcoding.moviesjetpack.core.domain.onSuccess
import com.agcoding.moviesjetpack.movies.domain.details.MovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id

    init {
        repository.movieID = movieId
    }

    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.onStart {
        fetchCredits()
        fetchMovieDetails()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    val similarMovies by lazy { repository.similarMoviesPagingFlow }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            repository.getMovieDetails(movieId)
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            details = data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }
                }
        }
    }

    private fun fetchCredits() {
        viewModelScope.launch {
            repository.getCredits(movieId)
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            credits = data,
                        )
                    }
                }
        }
    }
}
