package com.agcoding.moviesjetpack.core.data

data class Movie(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val description: String,
    val releaseDate: String,
)
