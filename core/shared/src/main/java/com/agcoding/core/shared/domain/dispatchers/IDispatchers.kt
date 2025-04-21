package com.agcoding.core.shared.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {
    fun mainThread(): CoroutineDispatcher
    fun backgroundThread(): CoroutineDispatcher
    fun defaultThread(): CoroutineDispatcher
}
