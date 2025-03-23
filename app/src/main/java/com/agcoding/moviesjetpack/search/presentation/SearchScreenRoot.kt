package com.agcoding.moviesjetpack.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.list.composables.SearchBar
import com.agcoding.moviesjetpack.search.presentation.composables.SearchItem
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import timber.log.Timber

@Composable
fun SearchScreenRoot(
    onMovieClick: (Movie) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchItems = viewModel.searchMovies.collectAsLazyPagingItems()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreen(
        onMovieClick = onMovieClick,
        searchItems = searchItems,
        searchQuery = searchQuery.value,
        onSearchQueryChange = {
            viewModel.updateQuery(it)
        }
    )
}

@Composable
fun SearchScreen(
    onMovieClick: (Movie) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchItems: LazyPagingItems<Movie>,
    searchQuery: String,
) {
    Timber.d("searched items ${searchItems.itemCount}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(vertical = 24.dp)
    ) {
        SearchBar(
            isAutoFocus = true,
            searchQuery = searchQuery,
            onSearchQueryChange = { query ->
                onSearchQueryChange(query)
            },
            onImeSearch = {
                onSearchQueryChange(searchQuery)
            },
            onTextFieldClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )
        Spacer(modifier = Modifier.size(12.dp))
        AnimatedVisibility(
            visible = searchItems.itemCount != 0,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        HorizontalDivider(color = Color.Gray)
                    }
                }
                item {
                    if (searchItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
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