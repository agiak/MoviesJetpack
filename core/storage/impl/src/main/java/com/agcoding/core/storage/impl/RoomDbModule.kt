package com.agcoding.core.storage.impl

import android.content.Context
import com.agcoding.core.storage.FavouriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDbModule {

    @Singleton
    @Provides
    fun provideFavouriteDao(db: FavouriteMoviesDatabase): FavouriteMovieDao = db.favouriteMovieDao()

    @Provides
    fun provideFavouriteMoviesDatabase(@ApplicationContext applicationContext: Context): FavouriteMoviesDatabase =
        RoomDbFactory.create(applicationContext)
}
