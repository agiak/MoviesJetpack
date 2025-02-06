package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.movies.data.mappers.toMovie
import com.agcoding.moviesjetpack.movies.data.movies
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun MovieItem(
    movie: Movie,
    onFavouriteClicked: () -> Unit,
    onClick: (Movie) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(284.dp)
            .padding(horizontal = 24.dp)
            .clickable { onClick(movie) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainImage(
                imageUrl = movie.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Released at ${movie.releaseDate}")
                    RatingBar(rating = movie.rating.toString())
                }
            }
        }
    }
}


@Composable
@Preview
fun MovieItemPreview() {
    MoviesJetpackTheme {
        MovieItem(
            movie = movies.map { it.toMovie() }[0],
            onFavouriteClicked = {},
            onClick = {}
        )
    }
}