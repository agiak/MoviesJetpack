package com.agcoding.moviesjetpack.movies.presentation.details.composables.similarMovies

import SimilarMovieItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.agcoding.moviesjetpack.core.presentation.composables.loaders.MainLoader
import com.agcoding.moviesjetpack.core.presentation.composables.messages.ErrorMessage
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.composables.getDummyLazyPagingItems
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import com.agcoding.moviesjetpack.ui.theme.primaryLight

internal const val SIMILAR_MOVIE_ITEM_HEIGHT = 124

@Composable
fun SimilarMoviesList(
    similarMovies: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Similar movies",
            style = TextStyle(
                fontSize = 16.sp,
                color = primaryLight
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        when (val state = similarMovies.loadState.refresh) {
            is LoadState.Error -> ErrorMessage(state.error.message ?: "")
            LoadState.Loading -> MainLoader(modifier = Modifier.height(SIMILAR_MOVIE_ITEM_HEIGHT.dp))
            else -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        count = similarMovies.itemCount,
                        key = similarMovies.itemKey { it.id },
                    ) { index ->
                        val item = similarMovies[index]
                        if (item != null) {
                            SimilarMovieItem(
                                movie = item,
                                onMovieClick = { onMovieClick(item) },
                            )
                        }
                    }
                    item {
                        if (similarMovies.loadState.append is LoadState.Loading) {
                            MainLoader(modifier = Modifier.height(SIMILAR_MOVIE_ITEM_HEIGHT.dp))
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark()
@Composable
fun SimilarMoviesListPreview() {
    MoviesJetpackTheme {
        SimilarMoviesList(
            similarMovies = getDummyLazyPagingItems(),
            onMovieClick = {}
        )
    }
}
