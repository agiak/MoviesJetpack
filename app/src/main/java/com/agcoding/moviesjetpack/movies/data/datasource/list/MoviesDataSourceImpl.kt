package com.agcoding.moviesjetpack.movies.data.datasource.list

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.MoviesResponse
import com.agcoding.moviesjetpack.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : MoviesDataSource {

    override suspend fun getMovies(
        page: Int,
        type: String
    ): Result<MoviesResponse, DataError.Remote> {
        return safeCall<MoviesResponse> {
            httpClient.get {
                url {
                    path("movie", type) // Append the type to the URL (e.g., "movie/popular")
                }
                parameter("language", "en-US")
                parameter("page", page.toString())
            }
        }
    }
}