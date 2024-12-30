package com.agcoding.moviesjetpack.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MoviesGraph : Route

    @Serializable
    data object MoviesList : Route

    @Serializable
    data class MovieDetail(val id: String) : Route

    @Serializable
    data object Search : Route
}