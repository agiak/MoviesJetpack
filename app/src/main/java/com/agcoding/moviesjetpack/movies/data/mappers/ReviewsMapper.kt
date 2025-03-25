package com.agcoding.moviesjetpack.movies.data.mappers

import com.agcoding.moviesjetpack.core.presentation.ext.toFormattedDate
import com.agcoding.moviesjetpack.movies.data.network.details.AuthorDetailsNetwork
import com.agcoding.moviesjetpack.movies.data.network.details.ReviewNetwork
import com.agcoding.moviesjetpack.movies.domain.details.AuthDetails
import com.agcoding.moviesjetpack.movies.domain.details.Review

fun ReviewNetwork.toReview(): Review = Review(
    id = id ?: "",
    content = content ?: "",
    author = authorDetailsNetwork?.toAuthDetailsInfo(),
    rating = authorDetailsNetwork?.rating ?: 0.0,
    createdAt = createdAt?.toFormattedDate() ?: "",
)

fun AuthorDetailsNetwork.toAuthDetailsInfo() =
    AuthDetails(
        name = name ?: "",
        username = username ?: "",
        avatarPath = avatarPath ?: "",
        rating = rating ?: 0.0,
    )
