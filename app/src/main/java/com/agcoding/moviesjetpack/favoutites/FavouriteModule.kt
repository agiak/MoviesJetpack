package com.agcoding.moviesjetpack.favoutites

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteModule {

    @Singleton
    @Binds
    abstract fun bindFavouriteUseCase(
        favouriteUseCaseImpl: FavouriteUseCaseImpl
    ): FavouritesUseCase

    @Singleton
    @Binds
    abstract fun bindIsFavouriteUseCase(
        isFavouriteUseCaseImpl: IsFavouriteUseCaseImpl
    ): IsFavouriteUseCase
}