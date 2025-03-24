package com.agcoding.moviesjetpack.favourites.di

import com.agcoding.moviesjetpack.favourites.data.repository.FavouritesRepositoryImpl
import com.agcoding.moviesjetpack.favourites.domain.FavouritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesScreenModule {

    @Singleton
    @Binds
    abstract fun bindFavouritesRepository(
        impl: FavouritesRepositoryImpl
    ): FavouritesRepository
} 