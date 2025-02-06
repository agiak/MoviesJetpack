package com.agcoding.moviesjetpack.core.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {
    fun mainThread(): CoroutineDispatcher
    fun backgroundThread(): CoroutineDispatcher
    fun defaultThread(): CoroutineDispatcher
}
