package com.agcoding.moviesjetpack.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.core.shared.theme.MoviesJetpackTheme
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.search.presentation.composables.SearchBar
import com.agcoding.moviesjetpack.search.presentation.composables.SearchItem

@Composable
fun SearchScreenRoot(
    onMovieClick: (Movie) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchItems = viewModel.movies.collectAsLazyPagingItems()

    SearchScreen(
        onMovieClick = onMovieClick,
        searchItems = searchItems,
        uiState = uiState,
        onSearchQueryChange = { query ->
            viewModel.updateQuery(query)
        },
        onRetry = {
            searchItems.retry()
        }
    )
}

@Composable
fun SearchScreen(
    onMovieClick: (Movie) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onRetry: () -> Unit,
    searchItems: LazyPagingItems<Movie>,
    uiState: SearchUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        SearchBar(
            isAutoFocus = true,
            searchQuery = uiState.searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onImeSearch = {
                onSearchQueryChange(uiState.searchQuery)
            },
            onTextFieldClicked = {},
            isLoading = uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(modifier = Modifier.size(16.dp))

        when (val refreshState = searchItems.loadState.refresh) {
            is LoadState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = refreshState.error.message.toString(),
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = onRetry
                        ) {
                            Text(text = "Retry")
                        }
                    }
                }
            }

            else -> {
                AnimatedVisibility(
                    visible = searchItems.itemCount != 0,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 500))
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(
                            count = searchItems.itemCount,
                            key = searchItems.itemKey { it.id },
                        ) { index ->
                            val item = searchItems[index]
                            if (item != null) {
                                SearchItem(
                                    movie = item,
                                    onClick = { onMovieClick(item) }
                                )
                            }
                        }
                        item {
                            if (searchItems.loadState.append is LoadState.Loading) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
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

@Composable
@PreviewLightDark
fun SearchScreenRootPreview() {
    MoviesJetpackTheme {
        SearchScreenRoot(
            onMovieClick = {},
        )
    }
}