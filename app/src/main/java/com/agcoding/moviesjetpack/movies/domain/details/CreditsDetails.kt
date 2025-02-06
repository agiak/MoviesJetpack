package com.agcoding.moviesjetpack.movies.domain.details

data class CreditsDetails(
    val cast: List<CastDetails> = emptyList(),
    val crew: List<CrewDetails> = emptyList(),
)

data class CrewDetails(
    val id: Int,
    val name: String,
    val department: String,
)

data class CastDetails(
    val id: Int,
    val name: String,
    val image: String? = null,
    val character: String,
)
