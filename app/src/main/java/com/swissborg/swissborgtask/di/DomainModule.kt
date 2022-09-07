package com.swissborg.swissborgtask.di

import com.swissborg.swissborgtask.data.datasource.TickersRepositoryImpl
import com.swissborg.swissborgtask.data.remote.services.TickersApiService
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideTickersRepository(
        tickersApiService: TickersApiService
    ): TickersRepository = TickersRepositoryImpl(tickersApiService = tickersApiService)
}
