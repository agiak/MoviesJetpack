package com.agcoding.moviesjetpack.storage.db

import android.content.Context
import androidx.room.Room

object RoomDbFactory {

    const val FAVOURITE_DB = "favourite_movies"

    fun create(applicationContext: Context): FavouriteMoviesDatabase =
        Room.databaseBuilder(
            applicationContext,
            FavouriteMoviesDatabase::class.java,
            FAVOURITE_DB
        ).build()
}
