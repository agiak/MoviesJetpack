package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.moviesjetpack.core.presentation.composables.messages.ErrorMessage
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.list.MoviesListUiState
import com.agcoding.moviesjetpack.movies.presentation.list.MoviesUiEvent
import com.agcoding.moviesjetpack.movies.presentation.list.MoviesViewModel

@Composable
fun MoviesListScreenRoot(
    viewModel: MoviesViewModel = hiltViewModel(),
    onSelectedMovie: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val movies = viewModel.moviesPagingFlow.collectAsLazyPagingItems()

    MoviesListScreen(
        state = state,
        movies = movies,
        onFavouriteClicked = { selectedMovie ->
            viewModel.onEvent(
                MoviesUiEvent.OnFavouriteClicked(
                    selectedMovie
                )
            )
        },
        onSelectedMovie = onSelectedMovie
    )
}

@Composable
fun MoviesListScreen(
    state: MoviesListUiState,
    movies: LazyPagingItems<Movie>,
    onFavouriteClicked: (Movie) -> Unit,
    onSelectedMovie: (Movie) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = movies.loadState.refresh) {
            is LoadState.Error -> ErrorMessage(state.error.message ?: "")
            LoadState.Loading -> CircularProgressIndicator()
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        count = movies.itemCount,
                        key = movies.itemKey { it.id },
                    ) { index ->
                        val item = movies[index]
                        if (item != null) {
                            MovieItem(
                                movie = item,
                                onFavouriteClicked = { onFavouriteClicked(item) },
                                onClick = onSelectedMovie
                            )
                        }
                    }
                    item {
                        if (movies.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}