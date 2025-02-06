package com.agcoding.moviesjetpack.movies.presentation.list.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.agcoding.moviesjetpack.movies.domain.list.Movie

@Composable
fun MoviesList(
    movies: List<Movie>,
) {
    LazyColumn { }
}