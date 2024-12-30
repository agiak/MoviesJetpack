package com.agcoding.moviesjetpack.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMovieDB(
    @PrimaryKey val id: Long,
    val title: String,
    val poster: String,
    val rating: Float,
    val releaseDate: String
)
