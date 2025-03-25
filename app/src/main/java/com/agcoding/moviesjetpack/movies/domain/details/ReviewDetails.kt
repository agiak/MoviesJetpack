package com.agcoding.moviesjetpack.movies.domain.details

data class Review(
    val id: String,
    val author: AuthDetails?,
    val content: String,
    val rating: Double,
    val createdAt: String,
)

data class AuthDetails(
    val name: String,
    val username: String,
    val avatarPath: String,
    val rating: Double
)
