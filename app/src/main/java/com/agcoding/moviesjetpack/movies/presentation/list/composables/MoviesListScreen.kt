package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.core.shared.presentation.UiText
import com.agcoding.core.shared.presentation.composables.loaders.MainLoader
import com.agcoding.core.shared.theme.MoviesJetpackTheme
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.composables.getDummyLazyPagingItems
import com.agcoding.moviesjetpack.movies.presentation.home.composables.HomeError
import com.agcoding.moviesjetpack.movies.presentation.list.MoviesListUiState
import com.agcoding.moviesjetpack.movies.presentation.list.MoviesViewModel

@Composable
fun MoviesListScreenRoot(
    onSelectedMovie: (Movie) -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val popularMovies = state.popularMovies.collectAsLazyPagingItems()
    val nowPlayingMovies = state.nowPlayingMovies.collectAsLazyPagingItems()

    MoviesListScreen(
        state = state,
        popularMovies = popularMovies,
        nowPlayingMovies = nowPlayingMovies,
        onSelectedMovie = onSelectedMovie,
        onRetry = { viewModel.onRetry() }
    )
}

@Composable
fun MoviesListScreen(
    state: MoviesListUiState,
    popularMovies: LazyPagingItems<Movie>,
    nowPlayingMovies: LazyPagingItems<Movie>,
    onSelectedMovie: (Movie) -> Unit,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                MainLoader(modifier = Modifier.size(48.dp))
            }

            state.error != null -> {
                HomeError(
                    error = state.error,
                    onRetry = onRetry
                )
            }

            popularMovies.loadState.refresh is LoadState.Error &&
            nowPlayingMovies.loadState.refresh is LoadState.Error -> {
                // Both sections failed to load
                HomeError(
                    error = state.error ?: UiText.DynamicString("Failed to load movies"),
                    onRetry = onRetry
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp)
                ) {
                    // Popular Movies Section
                    if (popularMovies.loadState.refresh !is LoadState.Error) {
                        Text(
                            text = "Popular Movies",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            items(
                                count = popularMovies.itemCount,
                                key = popularMovies.itemKey { it.id },
                            ) { index ->
                                val item = popularMovies[index]
                                if (item != null) {
                                    MovieItem(
                                        movie = item,
                                        onClick = onSelectedMovie,
                                        isHorizontal = true,
                                        isPopular = true
                                    )
                                }
                            }

                            if (popularMovies.loadState.append is LoadState.Loading) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .width(160.dp)
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Now Playing Movies Section
                    if (nowPlayingMovies.loadState.refresh !is LoadState.Error) {
                        Text(
                            text = "Now Playing",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            items(
                                count = nowPlayingMovies.itemCount,
                                key = nowPlayingMovies.itemKey { it.id },
                            ) { index ->
                                val item = nowPlayingMovies[index]
                                if (item != null) {
                                    NowPlayingItem(
                                        movie = item,
                                        onClick = onSelectedMovie
                                    )
                                }
                            }

                            if (nowPlayingMovies.loadState.append is LoadState.Loading) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .width(160.dp)
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun MoviesListScreenPreview() {
    MoviesJetpackTheme {
        MoviesListScreen(
            state = MoviesListUiState(),
            popularMovies = getDummyLazyPagingItems(),
            nowPlayingMovies = getDummyLazyPagingItems(),
            onSelectedMovie = {},
            onRetry = {}
        )
    }
}