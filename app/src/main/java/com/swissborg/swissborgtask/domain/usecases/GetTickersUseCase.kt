package com.swissborg.swissborgtask.domain.usecases

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.ui.TickerUIModel
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTickersUseCase @Inject constructor(
    private val tickerRepository: TickersRepository
) {

    suspend operator fun invoke(searchQuery: String): Result<List<TickerUIModel>> =
        withContext(Dispatchers.IO) {
            val tickers = tickerRepository.getTickers(GET_TICKERS_QUERY)
            if (tickers is Result.Error)
                return@withContext Result.Error(tickers.message ?: "Unknown error!")

            val symbolsFriendlyName = tickerRepository.getCurrencySymbol(GET_FRIENDLY_NAME_PATH)
            if (symbolsFriendlyName is Result.Error)
                return@withContext Result.Error(symbolsFriendlyName.message ?: "Unknown error!")

            val symbolsApiSymbol = tickerRepository.getCurrencySymbol(GET_API_SYMBOL_PATH)
            if (symbolsApiSymbol is Result.Error)
                return@withContext Result.Error(symbolsApiSymbol.message ?: "Unknown error!")

            Result.Success(
                tickers.data!!.map { ticker ->
                    TickerUIModel(
                        friendlyName = symbolsFriendlyName.data?.get(ticker.symbol),
                        apiName = symbolsApiSymbol.data?.get(ticker.symbol),
                        tickerModel = ticker
                    )
                }.filter {
                    it.friendlyName?.contains(searchQuery) == true
                            || it.apiName?.contains(searchQuery) == true
                            || it.tickerModel.symbol.contains(searchQuery)
                }
            )
        }

    companion object {
        const val GET_TICKERS_QUERY =
            "tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD,tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD,tOCEAN:USD,tBEST:USD,tAAVE:USD,tPLUUSD,tFILUSD"
        const val GET_FRIENDLY_NAME_PATH = "label"
        const val GET_API_SYMBOL_PATH = "sym"
    }
}
