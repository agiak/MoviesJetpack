package com.agcoding.moviesjetpack.core.data.models

import androidx.annotation.StringRes
import com.agcoding.moviesjetpack.R

enum class MovieType(val hex: String, @StringRes val description: Int) {
    POPULAR("popular", R.string.movie_type_popular),
    TOP_RATED("top_rated", R.string.movie_type_top_rated),
    UPCOMING("upcoming", R.string.movie_type_upcoming),
    NOW_PLAYING("now_playing", R.string.movie_type_now_playing)
}
