package com.agcoding.moviesjetpack.movies.data.datasource.details.factories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.agcoding.moviesjetpack.movies.data.datasource.details.ReviewsPagingSource
import com.agcoding.moviesjetpack.movies.data.network.details.ReviewNetwork

object ReviewsPagerFactory {
    private const val PAGE_SIZE = 20
    private const val FETCH_DISTANCE = 2

    fun create(dataSource: ReviewsPagingSource): Pager<Int, ReviewNetwork> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false,
                prefetchDistance = FETCH_DISTANCE
            ),
            pagingSourceFactory = { dataSource }
        )
} 