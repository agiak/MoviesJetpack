package com.agcoding.core.storage.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agcoding.core.storage.FavouriteMovieDB
import com.agcoding.core.storage.FavouriteMovieDao

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
