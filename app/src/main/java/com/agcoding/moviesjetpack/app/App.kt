package com.agcoding.moviesjetpack.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.agcoding.moviesjetpack.core.presentation.ext.sharedViewModel
import com.agcoding.moviesjetpack.movies.presentation.SelectedMovieViewModel
import com.agcoding.moviesjetpack.movies.presentation.details.MovieDetailScreenRoot
import com.agcoding.moviesjetpack.movies.presentation.list.composables.MoviesListScreenRoot
import com.agcoding.moviesjetpack.search.presentation.SearchScreenRoot
import com.agcoding.moviesjetpack.ui.theme.MoviesJetpackTheme
import timber.log.Timber

@Composable
fun App() {
    MoviesJetpackTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.MoviesGraph
        ) {
            navigation<Route.MoviesGraph>(
                startDestination = Route.MoviesList
            ) {
                composable<Route.MoviesList> { entry ->
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
                        },
                        onSearchClick = {
                            Timber.d("On search click")
                            navController.navigate(Route.Search)
                        }
                    )
                }

                composable<Route.MovieDetail> { entry ->
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

                composable<Route.Search> { entry ->

                    val selectedMovieViewModel =
                        entry.sharedViewModel<SelectedMovieViewModel>(navController)

                    SearchScreenRoot(
                        onBackClick = {
                            navController.navigateUp()
                        },
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
