package com.agcoding.moviesjetpack.movies.data.datasource.details

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agcoding.moviesjetpack.core.data.mappers.toPagingError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.details.ReviewNetwork

private const val STARTING_PAGE_INDEX = 1

class ReviewsPagingSource(
    private val dataSource: MovieDetailsDataSource,
    private val movieId: Long,
) : PagingSource<Int, ReviewNetwork>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewNetwork>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewNetwork> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            when (val result = dataSource.getReviews(movieId, page)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = result.data.reviewNetworks ?: emptyList(),
                        prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (result.data.reviewNetworks?.isEmpty() == true) null else page + 1
                    )
                }

                is Result.Error -> {
                    LoadResult.Error(result.error.toPagingError())
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }
} 