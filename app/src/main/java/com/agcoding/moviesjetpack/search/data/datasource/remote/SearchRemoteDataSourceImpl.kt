package com.agcoding.moviesjetpack.search.data.datasource.remote

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.network.safeCall
import com.agcoding.moviesjetpack.search.data.network.SearchMoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : SearchRemoteDataSource {

    override suspend fun searchMovies(
        query: String,
        page: Int
    ): Result<SearchMoviesResponse, DataError.Remote> {
        return safeCall<SearchMoviesResponse> {
            httpClient.get {
                url {
                    path("search", "movie")
                }
                parameter("query", query)
                parameter("language", "en-US")
                parameter("page", page.toString())
            }
        }
    }
}