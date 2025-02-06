package com.agcoding.moviesjetpack.movies.data.datasource.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agcoding.moviesjetpack.core.data.mappers.toPagingError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork
import timber.log.Timber
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource @Inject constructor(
    private val dataSource: MoviesDataSource,
    private val moviesType: String,
) : PagingSource<Int, MovieNetwork>() {

    override fun getRefreshKey(state: PagingState<Int, MovieNetwork>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieNetwork> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            when (val result = dataSource.getMovies(page, moviesType)) {
                is Result.Success -> {
                    Timber.d("Loaded page $page with result ${result.data.moviesNetwork.size} movies")
                    LoadResult.Page(
                        data = result.data.moviesNetwork,
                        prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                        nextKey = if (result.data.moviesNetwork.isEmpty()) null else page + 1
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