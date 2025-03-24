package com.agcoding.moviesjetpack.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.agcoding.moviesjetpack.core.presentation.composables.navigation.BottomNavItem
import com.agcoding.moviesjetpack.core.presentation.composables.navigation.BottomNavigationBar
import com.agcoding.moviesjetpack.core.presentation.ext.sharedViewModel
import com.agcoding.moviesjetpack.favourites.presentation.FavouritesScreenRoot
import com.agcoding.moviesjetpack.movies.presentation.SelectedMovieViewModel
import com.agcoding.moviesjetpack.movies.presentation.details.MovieDetailScreenRoot
import com.agcoding.moviesjetpack.movies.presentation.list.composables.MoviesListScreenRoot
import com.agcoding.moviesjetpack.search.presentation.SearchScreenRoot
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme

@Composable
fun App() {
    MoviesJetpackTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    items = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.Search,
                        BottomNavItem.Favourites
                    )
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Route.MainGraph,
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                navigation<Route.MainGraph>(
                    startDestination = Route.MoviesList
                ) {
                    composable<Route.MoviesList>(
                        enterTransition = {
                            when (initialState.destination.route) {
                                Route.MovieDetail::class.qualifiedName -> {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                        animationSpec = tween(500)
                                    )
                                }

                                else -> fadeIn(animationSpec = tween(500))
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                Route.MovieDetail::class.qualifiedName -> {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    )
                                }

                                else -> fadeOut(animationSpec = tween(500))
                            }
                        }
                    ) { entry ->
                        val selectedMovieViewModel =
                            entry.sharedViewModel<SelectedMovieViewModel>(navController)

                        LaunchedEffect(true) {
                            selectedMovieViewModel.onSelectMovie(null)
                        }

                        MoviesListScreenRoot(
                            onSelectedMovie = { movie ->
                                selectedMovieViewModel.onSelectMovie(movie)
                                navController.navigate(
                                    Route.MovieDetail(movie.id)
                                )
                            }
                        )
                    }

                    composable<Route.MovieDetail>(
                        enterTransition = {
                            when (initialState.destination.route) {
                                Route.MoviesList::class.qualifiedName -> {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    )
                                }

                                Route.MovieDetail::class.qualifiedName -> {
                                    slideIntoContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    )
                                }

                                else -> fadeIn(animationSpec = tween(500))
                            }
                        },
                        exitTransition = {
                            when (targetState.destination.route) {
                                Route.MoviesList::class.qualifiedName -> {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                        animationSpec = tween(500)
                                    )
                                }

                                Route.MovieDetail::class.qualifiedName -> {
                                    slideOutOfContainer(
                                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                        animationSpec = tween(500)
                                    )
                                }

                                else -> fadeOut(animationSpec = tween(500))
                            }
                        }
                    ) { entry ->
                        val selectedMovieViewModel =
                            entry.sharedViewModel<SelectedMovieViewModel>(navController)

                        val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

                        MovieDetailScreenRoot(
                            onBackClick = {
                                navController.navigateUp()
                            },
                            onSimilarMovieClick = { movie ->
                                selectedMovieViewModel.onSelectMovie(movie)
                                navController.navigate(
                                    Route.MovieDetail(movie.id)
                                )
                            }
                        )
                    }

                    composable<Route.Search>(
                        enterTransition = {
                            fadeIn(animationSpec = tween(500))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        }
                    ) { entry ->
                        val selectedMovieViewModel =
                            entry.sharedViewModel<SelectedMovieViewModel>(navController)

                        SearchScreenRoot(
                            onMovieClick = { movie ->
                                selectedMovieViewModel.onSelectMovie(movie)
                                navController.navigate(
                                    Route.MovieDetail(movie.id)
                                )
                            }
                        )
                    }

                    composable<Route.Favourites>(
                        enterTransition = {
                            fadeIn(animationSpec = tween(500))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(500))
                        }
                    ) { entry ->
                        val selectedMovieViewModel =
                            entry.sharedViewModel<SelectedMovieViewModel>(navController)

                        FavouritesScreenRoot(
                            onMovieClick = { movie ->
                                selectedMovieViewModel.onSelectMovie(movie)
                                navController.navigate(
                                    Route.MovieDetail(movie.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
