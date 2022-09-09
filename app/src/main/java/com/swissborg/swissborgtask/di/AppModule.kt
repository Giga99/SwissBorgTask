package com.swissborg.swissborgtask.di

import android.content.Context
import android.net.ConnectivityManager
import com.swissborg.swissborgtask.common.wrappers.NetworkConnectivityManager
import com.swissborg.swissborgtask.common.wrappers.NetworkConnectivityManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    @Singleton
    @Provides
    fun provideNetworkConnectivityManager(connectivityManager: ConnectivityManager): NetworkConnectivityManager =
        NetworkConnectivityManagerImpl(connectivityManager = connectivityManager)
}