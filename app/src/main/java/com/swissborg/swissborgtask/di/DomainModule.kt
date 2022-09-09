package com.swissborg.swissborgtask.di

import com.swissborg.swissborgtask.data.datasource.DefaultTickersRepositoryImpl
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun provideTickersRepository(defaultTickersRepositoryImpl: DefaultTickersRepositoryImpl): TickersRepository
}
