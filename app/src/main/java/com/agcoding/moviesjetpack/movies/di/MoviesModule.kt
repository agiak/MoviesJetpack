package com.agcoding.moviesjetpack.movies.di

import com.agcoding.core.shared.domain.dispatchers.IDispatchers
import com.agcoding.moviesjetpack.favoutites.FavouritesUseCase
import com.agcoding.moviesjetpack.favoutites.IsFavouriteUseCase
import com.agcoding.moviesjetpack.movies.data.datasource.details.MovieDetailsDataSource
import com.agcoding.moviesjetpack.movies.data.datasource.details.MovieDetailsDataSourceImpl
import com.agcoding.moviesjetpack.movies.data.datasource.list.MoviesDataSource
import com.agcoding.moviesjetpack.movies.data.datasource.list.MoviesDataSourceImpl
import com.agcoding.moviesjetpack.movies.data.repository.MoviesRepositoryImpl
import com.agcoding.moviesjetpack.movies.data.repository.dsetails.MovieDetailRepositoryImpl
import com.agcoding.moviesjetpack.movies.domain.details.MovieDetailsRepository
import com.agcoding.moviesjetpack.movies.domain.list.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    @Singleton
    fun providesMoviesRepository(
        moviesDataSource: MoviesDataSource,
        favouritesUseCase: FavouritesUseCase,
        dispatchers: IDispatchers,
        isFavouriteUseCase: IsFavouriteUseCase
    ): MoviesRepository =
        MoviesRepositoryImpl(
            moviesDataSource,
            favouritesUseCase,
            isFavouriteUseCase,
            dispatchers,
        )

    @Provides
    @Singleton
    fun providesMoviesDataSource(httpClient: HttpClient): MoviesDataSource =
        MoviesDataSourceImpl(httpClient)

    @Provides
    @Singleton
    fun providesMoviesDetailsDataSource(httpClient: HttpClient): MovieDetailsDataSource =
        MovieDetailsDataSourceImpl(httpClient)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesDetailsRepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(impl: MovieDetailRepositoryImpl): MovieDetailsRepository
}