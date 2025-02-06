package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun RatingBar(
    rating: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Rating: $rating")
        Icon(painter = painterResource(R.drawable.ic_rating), contentDescription = "Rating $rating")
    }
}

@Composable
@Preview
fun RatingBarPreview() {
    MoviesJetpackTheme {
        RatingBar("5,4")
    }
}