package com.agcoding.moviesjetpack.movies.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.movies.presentation.details.composables.GenreChips
import com.agcoding.moviesjetpack.movies.presentation.details.composables.MovieImage
import com.agcoding.moviesjetpack.movies.presentation.details.composables.cast.CastList
import com.agcoding.moviesjetpack.movies.presentation.details.composables.getDummyLazyPagingItems
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

    MovieDetailScreen(
        state = state,
        similarMovies = similarMoviesState,
        onBackClick = onBackClick,
        onSimilarMovieClick = onSimilarMovieClick
    )
}

@Composable
private fun MovieDetailScreen(
    state: MovieDetailsState,
    similarMovies: LazyPagingItems<Movie>,
    onBackClick: () -> Unit,
    onSimilarMovieClick: (Movie) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        with(state.details) {
            MovieImage(
                imageUrl = imageUrl,
                onBackClick = onBackClick,
                title = title
            )
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .clip(
                        RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    )
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = releaseDate,
                    style = TextStyle(
                        fontWeight = FontWeight.Light
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(rating = rating)
                Spacer(modifier = Modifier.height(16.dp))
                GenreChips(
                    genres = type,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Description",
                    style = TextStyle(color = primaryLight, fontSize = 16.sp),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = description, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(32.dp))
                CastList(state.credits.cast)
                Spacer(modifier = Modifier.height(24.dp))
                SimilarMoviesList(
                    similarMovies = similarMovies,
                    onMovieClick = { similarMovie -> onSimilarMovieClick(similarMovie) }
                )
            }
        }
    }
}

@Composable
private fun RatingBar(
    rating: Float
) {
    Row {
        for (i in 1..5) {
            val iconColor = if (i <= rating) primaryLight else Color.Gray
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = iconColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    MoviesJetpackTheme {
        MovieDetailScreen(
            state = MovieDetailsState(
                details = MovieDetails(
                    title = "Movie Title",
                    description = "Movie Description",
                    releaseDate = "18/12/24",
                    rating = 3.2F,
                    imageUrl = "",
                    isFavourite = true,
                    type = "Horror, Thriller"
                )
            ),
            similarMovies = getDummyLazyPagingItems(),
            onBackClick = {},
            onSimilarMovieClick = {}
        )
    }
}
