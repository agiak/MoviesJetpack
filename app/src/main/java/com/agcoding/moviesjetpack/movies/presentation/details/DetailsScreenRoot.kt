package com.agcoding.moviesjetpack.movies.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.agcoding.moviesjetpack.movies.domain.details.Review
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.composables.GenreChips
import com.agcoding.moviesjetpack.movies.presentation.details.composables.MovieImage
import com.agcoding.moviesjetpack.movies.presentation.details.composables.cast.CastList
import com.agcoding.moviesjetpack.movies.presentation.details.composables.getDummyLazyPagingItems
import com.agcoding.moviesjetpack.movies.presentation.details.composables.getDummyLazyPagingItemsReviews
import com.agcoding.moviesjetpack.movies.presentation.details.composables.reviews.ReviewsList
import com.agcoding.moviesjetpack.movies.presentation.details.composables.similarMovies.SimilarMoviesList
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import com.agcoding.moviesjetpack.ui.theme.primaryLight

@Composable
fun MovieDetailScreenRoot(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSimilarMovieClick: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val similarMoviesState = viewModel.similarMovies.collectAsLazyPagingItems()
    val reviewsState = viewModel.reviews.collectAsLazyPagingItems()

    MovieDetailScreen(
        state = state,
        similarMovies = similarMoviesState,
        reviews = reviewsState,
        onBackClick = onBackClick,
        onSimilarMovieClick = onSimilarMovieClick,
        onFavoriteClick = { viewModel.onAction(MovieDetailAction.OnFavouriteClicked) }
    )
}

@Composable
private fun MovieDetailScreen(
    state: MovieDetailsState,
    similarMovies: LazyPagingItems<Movie>,
    reviews: LazyPagingItems<Review>,
    onBackClick: () -> Unit,
    onSimilarMovieClick: (Movie) -> Unit,
    onFavoriteClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            MovieImage(
                title = state.details.title,
                imageUrl = state.details.imageUrl,
                isFavorite = state.details.isFavourite,
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = state.details.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = primaryLight
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = state.details.rating.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.details.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                GenreChips(state.details.type)

                Spacer(modifier = Modifier.height(24.dp))

                CastList(state.credits.cast)

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        item {
            ReviewsList(reviews)
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            SimilarMoviesList(
                similarMovies = similarMovies,
                onMovieClick = onSimilarMovieClick
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MoviesJetpackTheme {
        MovieDetailScreen(
            state = MovieDetailsState(),
            similarMovies = getDummyLazyPagingItems(),
            reviews = getDummyLazyPagingItemsReviews(),
            onBackClick = {},
            onSimilarMovieClick = {},
            onFavoriteClick = {}
        )
    }
}
