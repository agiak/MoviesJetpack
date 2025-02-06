package com.agcoding.moviesjetpack.favoutites

fun interface IsFavouriteUseCase {

    suspend operator fun invoke(movieId: Long): Boolean
}