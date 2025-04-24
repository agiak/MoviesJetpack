package com.agcoding.moviesjetpack.search.presentation

import androidx.paging.PagingData
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val searchQuery: String = "",
    val searchItems: Flow<PagingData<Movie>>? = null,
    val isLoading: Boolean = false,
) 