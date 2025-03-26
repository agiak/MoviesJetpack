package com.agcoding.moviesjetpack.favourites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agcoding.moviesjetpack.favourites.presentation.composables.FavouriteMovieItem
import com.agcoding.moviesjetpack.movies.data.uiMovies
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavouritesScreenRoot(
    onMovieClick: (Movie) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavouritesScreen(
        state = state,
        onMovieClick = onMovieClick,
        onFavouriteClick = viewModel::onRemoveFavourite
    )
}

@Composable
fun FavouritesScreen(
    state: FavouritesUiState,
    onMovieClick: (Movie) -> Unit,
    onFavouriteClick: (Movie) -> Unit
) {
    val visibleItems = remember { mutableStateMapOf<Long, Boolean>() }

    state.favouriteMovies.forEach { movie ->
        if (!visibleItems.containsKey(movie.id)) {
            visibleItems[movie.id] = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.favouriteMovies.isEmpty() -> Text(
                text = "No favourite movies yet.\nAdd some movies to your favourites!",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(32.dp)
            )

            else -> LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.favouriteMovies,
                    key = { it.id }
                ) { movie ->
                    FavouriteMovieItem(
                        movie = movie,
                        onFavouriteClick = {
                            visibleItems[movie.id] = false
                            kotlinx.coroutines.MainScope().launch {
                                delay(300)
                                onFavouriteClick(movie)
                            }
                        },
                        onClick = onMovieClick,
                        visible = visibleItems[movie.id] ?: true
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun FavouritesScreenPreview() {
    MoviesJetpackTheme {
        FavouritesScreen(
            state = FavouritesUiState(
                favouriteMovies = uiMovies.map { it.copy(isFavourite = true) },
                isLoading = false
            ),
            onMovieClick = {},
            onFavouriteClick = {}
        )
    }
} 