package com.swissborg.swissborgtask.domain.repositories

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.ui.TickerModel

interface TickersRepository {

    suspend fun getTickers(symbols: String): Result<List<TickerModel>>

    suspend fun getCurrencySymbol(detail: String): Result<Map<String, String>>
}
