package com.agcoding.moviesjetpack.movies.domain.list

data class Movie(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val rating: Float,
    val description: String,
    val releaseDate: String,
    var isFavourite: Boolean = false,
) {
    override fun toString(): String = "Movie(id=$id, title=$title)"
}
