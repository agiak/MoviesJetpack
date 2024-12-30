package com.agcoding.moviesjetpack.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agcoding.moviesjetpack.core.data.models.FavouriteMovieDB

@Database(
    entities = [
        FavouriteMovieDB::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FavouriteMoviesDatabase : RoomDatabase() {

    abstract fun favouriteMovieDao(): FavouriteMovieDao
}
