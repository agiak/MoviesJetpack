package com.agcoding.moviesjetpack.movies.data.datasource.details.factories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.agcoding.moviesjetpack.movies.data.datasource.details.SimilarMoviesPagingSource
import com.agcoding.moviesjetpack.movies.data.network.details.SimilarMovieNetwork

object SimilarMoviesPagerFactory {
    private const val PAGE_SIZE = 30
    private const val FETCH_DISTANCE = 2

    fun create(dataSource: SimilarMoviesPagingSource): Pager<Int, SimilarMovieNetwork> =
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
