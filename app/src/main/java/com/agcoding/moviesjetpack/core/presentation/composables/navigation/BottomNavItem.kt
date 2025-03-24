package com.agcoding.moviesjetpack.core.presentation.composables.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.agcoding.moviesjetpack.R
import com.agcoding.moviesjetpack.app.Route

sealed class BottomNavItem(
    val route: Route,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val labelResId: Int
) {
    data object Home : BottomNavItem(
        route = Route.MoviesList,
        icon = Icons.Default.Home,
        labelResId = R.string.movies
    )

    data object Search : BottomNavItem(
        route = Route.Search,
        icon = Icons.Default.Search,
        labelResId = R.string.search
    )

    data object Favourites : BottomNavItem(
        route = Route.Favourites,
        icon = Icons.Default.Favorite,
        labelResId = R.string.favourites
    )
}