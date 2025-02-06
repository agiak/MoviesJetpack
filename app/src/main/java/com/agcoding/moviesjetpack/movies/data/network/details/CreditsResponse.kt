package com.agcoding.moviesjetpack.movies.data.network.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast") val castList: List<Cast>? = null,
    @SerialName("crew") val crewList: List<Crew>? = null,
    @SerialName("id") val id: Long? = null
) {
    fun getDirector(): String {
        return crewList
            ?.filter { it.department == CrewDepartment.DIRECTING.department }
            ?.joinToString(", ") { it.name ?: "" } ?: ""
    }

    fun getCast(): String {
        return castList
            ?.filter { it.knownForDepartment == CastDepartment.ACTING.department }
            ?.joinToString(", ") { it.name ?: "" } ?: ""
    }
}

@Serializable
data class Cast(
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("cast_id") val castId: Int? = null,
    @SerialName("character") val character: String? = null,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("gender") val gender: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("profile_path") val profilePath: String? = null,
)

@Serializable
data class Crew(
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("department") val department: String? = null,
    @SerialName("gender") val gender: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("job") val job: String? = null,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("profile_path") val profilePath: String? = null,
)

enum class CrewDepartment(val department: String) {
    DIRECTING("Directing")
}

enum class CastDepartment(val department: String) {
    ACTING("Acting")
}