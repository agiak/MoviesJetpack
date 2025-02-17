package com.agcoding.moviesjetpack.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.moviesjetpack.core.presentation.composables.buttons.BackButton
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.list.composables.SearchBar
import com.agcoding.moviesjetpack.search.presentation.composables.SearchItem
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun SearchScreenRoot(
    onBackClick: () -> Unit,
    onMovieClick: (Movie) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchItems = viewModel.searchMovies.collectAsLazyPagingItems()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()

    SearchScreen(
        onBackClick = onBackClick,
        onMovieClick = onMovieClick,
        searchItems = searchItems,
        searchQuery = searchQuery.value,
        onSearchQueryChange = { viewModel.updateQuery(it) }
    )
}

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onMovieClick: (Movie) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchItems: LazyPagingItems<Movie>,
    searchQuery: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BackButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackClick
            )
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
                    .fillMaxWidth(),
            )
        }
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

@Composable
@PreviewLightDark
fun SearchScreenRootPreview() {
    MoviesJetpackTheme {
        SearchScreenRoot(
            onBackClick = {},
            onMovieClick = {},
        )
    }
}