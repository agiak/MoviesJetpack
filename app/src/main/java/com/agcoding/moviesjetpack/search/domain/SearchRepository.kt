package com.agcoding.moviesjetpack.search.domain

import androidx.paging.Pager
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork

fun interface SearchRepository {
    fun searchMovies(query: String): Pager<Int, MovieNetwork>
}