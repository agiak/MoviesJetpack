package com.agcoding.moviesjetpack.movies.presentation.details.composables

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.agcoding.moviesjetpack.movies.domain.list.Movie
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun getDummyLazyPagingItems(): LazyPagingItems<Movie> {
    val dummyMovies = listOf(
        Movie(1, "Movie 1", "https://example.com/image1.jpg", 4.5f, "Description 1", "2024-01-01"),
        Movie(2, "Movie 2", "https://example.com/image2.jpg", 3.8f, "Description 2", "2024-02-01"),
        Movie(3, "Movie 3", "https://example.com/image3.jpg", 4.2f, "Description 3", "2024-03-01"),
        Movie(4, "Movie 4", "https://example.com/image4.jpg", 4.7f, "Description 4", "2024-04-01"),
        Movie(5, "Movie 5", "https://example.com/image5.jpg", 3.9f, "Description 5", "2024-05-01")
    )

    val pagingData = PagingData.from(dummyMovies)
    val flow = flowOf(pagingData)
    return flow.collectAsLazyPagingItems()
}