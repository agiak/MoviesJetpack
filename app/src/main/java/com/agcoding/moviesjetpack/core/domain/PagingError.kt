package com.agcoding.moviesjetpack.core.domain

sealed class PagingError : Throwable() {
    sealed class Remote : PagingError() {
        data object REQUEST_TIMEOUT : Remote()
        data object TOO_MANY_REQUESTS : Remote()
        data object NO_INTERNET : Remote()
        data object SERVER : Remote()
        data object SERIALIZATION : Remote()
        data class UNKNOWN(override val message: String) : Remote()
    }

    sealed class Local : PagingError() {
        data object DISK_FULL : Local()
        data object UNKNOWN : Local()
    }
}