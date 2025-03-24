package com.agcoding.moviesjetpack.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MoviesGraph : Route

    @Serializable
    data object MoviesList : Route

    @Serializable
    data class MovieDetail(val id: Long) : Route

    @Serializable
    data object Search : Route

    @Serializable
    data object MainGraph : Route

    @Serializable
    data object More : Route

    @Serializable
    data object Favourites : Route
}