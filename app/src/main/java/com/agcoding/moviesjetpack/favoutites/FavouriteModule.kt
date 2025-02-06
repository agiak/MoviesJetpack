package com.agcoding.moviesjetpack.favoutites

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouriteModule {

    @Binds
    abstract fun bindFavouriteUseCase(
        favouriteUseCaseImpl: FavouriteUseCaseImpl
    ): FavouritesUseCase

    @Binds
    abstract fun bindIsFavouriteUseCase(
        isFavouriteUseCaseImpl: IsFavouriteUseCaseImpl
    ): IsFavouriteUseCase
}