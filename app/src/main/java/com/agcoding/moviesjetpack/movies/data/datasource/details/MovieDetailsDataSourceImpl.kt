package com.agcoding.moviesjetpack.movies.data.datasource.details

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.details.CreditsResponse
import com.agcoding.moviesjetpack.movies.data.network.details.MovieDetailsResponse
import com.agcoding.moviesjetpack.movies.data.network.details.SimilarResponse
import com.agcoding.moviesjetpack.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject

class MovieDetailsDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieDetailsDataSource {

    override suspend fun getMovieDetails(id: Long): Result<MovieDetailsResponse, DataError.Remote> {
        return safeCall<MovieDetailsResponse> {
            httpClient.get {
                url {
                    path(
                        "movie",
                        id.toString()
                    )
                }
                parameter("language", "en-US")
            }
        }
    }

    override suspend fun getCredits(id: Long): Result<CreditsResponse, DataError.Remote> {
        return safeCall<CreditsResponse> {
            httpClient.get {
                url {
                    path(
                        "movie",
                        id.toString(),
                        "credits"
                    )
                }
                parameter("language", "en-US")
            }
        }
    }

    override suspend fun getSimilarMovies(
        id: Long,
        page: Int
    ): Result<SimilarResponse, DataError.Remote> {
        return safeCall<SimilarResponse> {
            httpClient.get {
                url {
                    path(
                        "movie",
                        id.toString(),
                        "similar"
                    )
                }
                parameter("language", "en-US")
                parameter("page", page.toString())
            }
        }
    }
}