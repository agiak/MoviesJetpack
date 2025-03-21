package com.agcoding.moviesjetpack.search.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.agcoding.moviesjetpack.core.presentation.images.MainImage
import com.agcoding.moviesjetpack.movies.data.uiMovies
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun SearchItem(
    movie: Movie,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        MainImage(
            imageUrl = movie.imageUrl,
            modifier = Modifier
                .size(36.dp)
        )
        Text(
            text = movie.title,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
@PreviewLightDark
private fun SearchItemPreview() {
    MoviesJetpackTheme {
        SearchItem(
            movie = uiMovies[0],
            onClick = {}
        )
    }
}