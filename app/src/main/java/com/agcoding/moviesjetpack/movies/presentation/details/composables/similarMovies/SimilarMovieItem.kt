package com.agcoding.moviesjetpack.movies.presentation.details.composables.similarMovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.movies.data.uiMovies
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun SimilarMovieItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit,
) {
    Column(
        modifier = Modifier
            .width(SIMILAR_MOVIE_ITEM_HEIGHT.dp)
            .clickable { onMovieClick(movie) }
    ) {
        MainImage(
            imageUrl = movie.imageUrl,
            modifier = Modifier
                .size(SIMILAR_MOVIE_ITEM_HEIGHT.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Text(
            text = movie.title,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = movie.rating.toString()
            )
            Icon(
                painter = painterResource(R.drawable.ic_rating),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimilarMovieItemPreview() {
    MoviesJetpackTheme {
        SimilarMovieItem(
            movie = uiMovies[0],
            onMovieClick = {}
        )
    }
}