package com.agcoding.moviesjetpack.movies.data.network.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewsResponse(
    @SerialName("results") val reviewNetworks: List<ReviewNetwork>? = null,
    @SerialName("page") val page: Int? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null,
)

@Serializable
data class ReviewNetwork(
    @SerialName("id") val id: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("author_details") val authorDetailsNetwork: AuthorDetailsNetwork? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("url") val url: String? = null,
)

@Serializable
data class AuthorDetailsNetwork(
    @SerialName("avatar_path") val avatarPath: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("rating") val rating: Double? = null,
    @SerialName("username") val username: String? = null,
)
