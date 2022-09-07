package com.swissborg.swissborgtask.di

import com.squareup.moshi.JsonAdapter
import com.swissborg.swissborgtask.data.remote.adapters.MoshiBigDecimalAdapter
import com.swissborg.swissborgtask.data.remote.adapters.MoshiSymbolAdapter
import com.swissborg.swissborgtask.data.remote.adapters.MoshiTickerAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkAdaptersModule {

    @Binds
    @IntoSet
    abstract fun bindMoshiBigDecimalAdapter(adapter: MoshiBigDecimalAdapter): JsonAdapter<*>

    @Binds
    @IntoSet
    abstract fun bindMoshiTickerAdapter(adapter: MoshiTickerAdapter): JsonAdapter<*>

    @Binds
    @IntoSet
    abstract fun bindMoshiSymbolAdapter(adapter: MoshiSymbolAdapter): JsonAdapter<*>
}
