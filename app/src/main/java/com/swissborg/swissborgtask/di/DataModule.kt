package com.swissborg.swissborgtask.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.swissborg.swissborgtask.common.core.Constants.API_BASE_URL
import com.swissborg.swissborgtask.data.remote.services.TickersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideMoshi(
        adapters: Set<@JvmSuppressWildcards JsonAdapter<*>>
    ): Moshi = Moshi.Builder()
        .apply { adapters.forEach { add(it) } }
        .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideTickersApiService(
        retrofit: Retrofit
    ): TickersApiService = retrofit.create(TickersApiService::class.java)
}
