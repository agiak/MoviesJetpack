package com.agcoding.moviesjetpack.search.data.network

import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val moviesNetwork: List<MovieNetwork>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)
