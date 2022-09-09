package com.swissborg.swissborgtask.di

import android.content.Context
import android.net.ConnectivityManager
import com.swissborg.swissborgtask.common.wrappers.connectivity.NetworkConnectivityManager
import com.swissborg.swissborgtask.common.wrappers.connectivity.NetworkConnectivityManagerImpl
import com.swissborg.swissborgtask.common.wrappers.dispatchers.DefaultDispatcherProviderImpl
import com.swissborg.swissborgtask.common.wrappers.dispatchers.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindNetworkConnectivityManager(networkConnectivityManagerImpl: NetworkConnectivityManagerImpl): NetworkConnectivityManager

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(defaultDispatcherProviderImpl: DefaultDispatcherProviderImpl): DispatcherProvider

    companion object {
        @Singleton
        @Provides
        fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }
}
