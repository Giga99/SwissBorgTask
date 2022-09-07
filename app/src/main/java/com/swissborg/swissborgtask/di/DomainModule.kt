package com.swissborg.swissborgtask.di

import com.swissborg.swissborgtask.data.datasource.TickerRepositoryImpl
import com.swissborg.swissborgtask.data.remote.services.TickersApiService
import com.swissborg.swissborgtask.domain.repositories.TickerRepository
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
    fun provideTickerRepository(
        tickersApiService: TickersApiService
    ): TickerRepository = TickerRepositoryImpl(tickersApiService = tickersApiService)
}
