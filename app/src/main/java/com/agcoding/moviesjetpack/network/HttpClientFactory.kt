package com.agcoding.moviesjetpack.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val HEADER_AUTH = "Authorization"
private const val AUTH_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MjhkYjc0OGQ0ZDczOGEyMjhmNGM4OGVkYzA1YTI3NSIsInN1YiI6IjY0ODA5NWNmOTkyNTljMDBlMmYyZDljMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T9BMoyeYnCgihW0iOXFBaEVyIPF6Z8BG3GiUKxmK8Lw"

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                url(BASE_URL)
                headers {
                    header(HEADER_AUTH, "Bearer $AUTH_KEY")
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}