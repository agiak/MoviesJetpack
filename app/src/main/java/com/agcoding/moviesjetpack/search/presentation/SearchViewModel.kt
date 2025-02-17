package com.agcoding.moviesjetpack.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.agcoding.moviesjetpack.movies.data.mappers.toMovie
import com.agcoding.moviesjetpack.search.domain.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchMovies = _searchQuery.filter { it.isNotBlank() }
        .debounce(1000)
        .flatMapLatest { query ->
            repository.searchMovies(query).flow.map { pagingData ->
                pagingData.map {
                    it.toMovie()
                }
            }
        }.cachedIn(viewModelScope)


    fun updateQuery(q: String) {
        _searchQuery.update { q }
    }
}