package com.agcoding.moviesjetpack.search.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agcoding.moviesjetpack.core.data.mappers.toPagingError
import com.agcoding.moviesjetpack.core.domain.Result
import com.agcoding.moviesjetpack.movies.data.network.MovieNetwork
import com.agcoding.moviesjetpack.search.data.datasource.remote.SearchRemoteDataSource
import timber.log.Timber

private const val STARTING_PAGE_INDEX = 1

class SearchPagingSource(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val query: String,
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
            when (val result = searchRemoteDataSource.searchMovies(page = page, query = query)) {
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