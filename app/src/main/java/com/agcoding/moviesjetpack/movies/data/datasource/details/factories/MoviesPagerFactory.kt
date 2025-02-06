package com.agcoding.moviesjetpack.movies.data.datasource.details.factories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.agcoding.moviesjetpack.movies.data.datasource.list.MoviesPagingSource
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork

object MoviesPagerFactory {
    private const val PAGE_SIZE = 30
    private const val FETCH_DISTANCE = 2

    fun create(dataSource: MoviesPagingSource): Pager<Int, MovieNetwork> =
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