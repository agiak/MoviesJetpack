package com.agcoding.moviesjetpack.core.data.mappers

import com.agcoding.moviesjetpack.core.domain.DataError
import com.agcoding.moviesjetpack.core.domain.PagingError

fun DataError.toPagingError(): PagingError =
    when (this) {
        DataError.Local.DISK_FULL -> PagingError.Local.DISK_FULL
        DataError.Local.UNKNOWN -> PagingError.Local.UNKNOWN
        DataError.Remote.REQUEST_TIMEOUT -> PagingError.Remote.REQUEST_TIMEOUT
        DataError.Remote.TOO_MANY_REQUESTS -> PagingError.Remote.TOO_MANY_REQUESTS
        DataError.Remote.NO_INTERNET -> PagingError.Remote.NO_INTERNET
        DataError.Remote.SERVER -> PagingError.Remote.SERVER
        DataError.Remote.SERIALIZATION -> PagingError.Remote.SERIALIZATION
        DataError.Remote.UNKNOWN -> PagingError.Remote.UNKNOWN("")
    }