package com.agcoding.moviesjetpack.storage.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.agcoding.moviesjetpack.core.data.models.FavouriteMovieDB
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMovieDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavoriteMovies(): Flow<List<FavouriteMovieDB>>

    @Insert
    suspend fun insertFavoriteMovie(movie: FavouriteMovieDB)

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavouriteMovieDB)

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_movies WHERE id = :movieId LIMIT 1)")
    suspend fun isMovieFavorite(movieId: Long): Boolean
}
