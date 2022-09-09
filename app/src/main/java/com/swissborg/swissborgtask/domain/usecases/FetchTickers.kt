package com.swissborg.swissborgtask.domain.usecases

import com.swissborg.swissborgtask.common.core.Constants.GET_API_SYMBOL_PATH
import com.swissborg.swissborgtask.common.core.Constants.GET_FRIENDLY_NAME_PATH
import com.swissborg.swissborgtask.common.core.Constants.GET_TICKERS_QUERY
import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.common.wrappers.dispatchers.DispatcherProvider
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchTickers @Inject constructor(
    private val tickerRepository: TickersRepository,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend operator fun invoke(): Result<Unit> =
        withContext(dispatcherProvider.io()) {
            val tickers = tickerRepository.fetchTickers(GET_TICKERS_QUERY)
            if (tickers is Result.Error)
                return@withContext Result.Error(tickers.message ?: "Unknown error!")

            val symbolsFriendlyName = tickerRepository.fetchCurrencySymbol(GET_FRIENDLY_NAME_PATH)
            if (symbolsFriendlyName is Result.Error)
                return@withContext Result.Error(symbolsFriendlyName.message ?: "Unknown error!")

            val symbolsApiSymbol = tickerRepository.fetchCurrencySymbol(GET_API_SYMBOL_PATH)
            if (symbolsApiSymbol is Result.Error)
                return@withContext Result.Error(symbolsApiSymbol.message ?: "Unknown error!")

            Result.Success(Unit)
        }
}
