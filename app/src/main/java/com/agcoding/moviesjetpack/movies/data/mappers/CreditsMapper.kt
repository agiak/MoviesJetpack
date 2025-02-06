package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.moviesjetpack.movies.data.network.details.Cast
import com.agcoding.moviesjetpack.movies.data.network.details.CreditsResponse
import com.agcoding.moviesjetpack.movies.data.network.details.Crew
import com.agcoding.moviesjetpack.movies.domain.details.CastDetails
import com.agcoding.moviesjetpack.movies.domain.details.CreditsDetails
import com.agcoding.moviesjetpack.movies.domain.details.CrewDetails

fun Crew.toCrewDetails(): CrewDetails = CrewDetails(
    id = id ?: 0,
    name = name ?: "",
    department = department ?: ""
)

fun Cast.toCastDetails(): CastDetails = CastDetails(
    id = id ?: 0,
    name = name ?: "",
    character = character ?: "",
    image = profilePath?.takeIf { it.isNotBlank() }?.let { URL_POSTER + it }
)

fun CreditsResponse.toCreditsDetails(): CreditsDetails =
    CreditsDetails(
        crew = crewList?.map { it.toCrewDetails() } ?: emptyList(),
        cast = castList?.map { it.toCastDetails() } ?: emptyList(),
    )