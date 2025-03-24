package com.agcoding.moviesjetpack.favourites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agcoding.moviesjetpack.favourites.domain.FavouritesRepository
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: FavouritesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesUiState())
    val state = repository.getFavouriteMovies()
        .map { movies ->
            FavouritesUiState(
                favouriteMovies = movies,
                isLoading = false
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            FavouritesUiState()
        )

    fun onRemoveFavourite(movie: Movie) {
        viewModelScope.launch {
            repository.removeFavourite(movie.id)
        }
    }
} 