package com.agcoding.moviesjetpack.movies.data.datasource.details

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agcoding.moviesjetpack.core.data.mappers.toPagingError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.details.SimilarMovieNetwork

private const val STARTING_PAGE_INDEX = 1

class SimilarMoviesPagingSource(
    private val dataSource: MovieDetailsDataSource,
    private val moviesId: Long,
) : PagingSource<Int, SimilarMovieNetwork>() {

    override fun getRefreshKey(state: PagingState<Int, SimilarMovieNetwork>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimilarMovieNetwork> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            when (val result = dataSource.getSimilarMovies(moviesId, page)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = result.data.similarMovieNetworks ?: emptyList(),
                        prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (result.data.similarMovieNetworks?.isEmpty() == true) null else page + 1
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