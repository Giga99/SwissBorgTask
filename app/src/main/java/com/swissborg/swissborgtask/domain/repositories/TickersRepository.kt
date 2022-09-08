package com.swissborg.swissborgtask.domain.repositories

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import kotlinx.coroutines.flow.Flow

interface TickersRepository {

    suspend fun fetchTickers(symbols: String): Result<Unit>

    suspend fun fetchCurrencySymbol(detail: String): Result<Unit>

    suspend fun searchTickers(searchQuery: String): Flow<List<TickerModel>>
}
