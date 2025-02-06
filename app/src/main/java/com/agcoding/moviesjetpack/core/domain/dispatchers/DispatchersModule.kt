package com.agcoding.moviesjetpack.core.domain.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @Singleton
    fun providesCoroutineDispatcherIO(): IDispatchers =
        DispatchersImpl()
}
