package com.swissborg.swissborgtask

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.enums.TickerType.TradingPair
import com.swissborg.swissborgtask.domain.models.ui.TickerDetails
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal

class TestTickersRepositoryImpl() : TickersRepository {

    override suspend fun fetchTickers(symbols: String): Result<Unit> =
        if (symbols == "error") Result.Error("ERROR!")
        else Result.Success(Unit)

    override suspend fun fetchCurrencySymbol(detail: String): Result<Unit> =
        when (detail) {
            "friendlyNameError" -> Result.Error("FRIENDLY NAME ERROR!")
            "apiSymbolError" -> Result.Error("API SYMBOL ERROR!")
            else -> Result.Success(Unit)
        }

    override suspend fun searchTickers(searchQuery: String): Flow<List<TickerModel>> = flow {
        emit(fullList)
    }

    companion object {
        val fullList = listOf(
            TickerModel(
                friendlyName = "Bitcoin",
                apiName = null,
                tickerDetails = TickerDetails(
                    symbol = "BTC",
                    type = TradingPair,
                    flashReturnRate = null,
                    bid = BigDecimal.valueOf(21027.0),
                    bidPeriod = null,
                    bidSize = BigDecimal.valueOf(36.23092416),
                    ask = BigDecimal.valueOf(21028.0),
                    askPeriod = null,
                    askSize = BigDecimal.valueOf(84.10841371000002),
                    dailyChange = BigDecimal.valueOf(1709.0),
                    dailyChangeRelative = BigDecimal.valueOf(0.0885),
                    lastPrice = BigDecimal.valueOf(21027.0),
                    volume = BigDecimal.valueOf(13773.04386619),
                    high = BigDecimal.valueOf(21216.48419024),
                    low = BigDecimal.valueOf(19029.17130173),
                    flashReturnRateAmountAvailable = null
                )
            ),
            TickerModel(
                friendlyName = "Ethereum",
                apiName = null,
                tickerDetails = TickerDetails(
                    symbol = "ETH",
                    type = TradingPair,
                    flashReturnRate = null,
                    bid = BigDecimal.valueOf(1705.4),
                    bidPeriod = null,
                    bidSize = BigDecimal.valueOf(433.317893),
                    ask = BigDecimal.valueOf(1705.5),
                    askPeriod = null,
                    askSize = BigDecimal.valueOf(453.98475802000013),
                    dailyChange = BigDecimal.valueOf(69.0631554),
                    dailyChangeRelative = BigDecimal.valueOf(0.0422),
                    lastPrice = BigDecimal.valueOf(1705.4631554),
                    volume = BigDecimal.valueOf(72173.02145688),
                    high = BigDecimal.valueOf(1717.1),
                    low = BigDecimal.valueOf(1599.2),
                    flashReturnRateAmountAvailable = null
                )
            ),
            TickerModel(
                friendlyName = "SwissBorg",
                apiName = null,
                tickerDetails = TickerDetails(
                    symbol = "CHSB",
                    type = TradingPair,
                    flashReturnRate = null,
                    bid = BigDecimal.valueOf(0.18347),
                    bidPeriod = null,
                    bidSize = BigDecimal.valueOf(109656.71831263001),
                    ask = BigDecimal.valueOf(0.18509),
                    askPeriod = null,
                    askSize = BigDecimal.valueOf(59140.22553970999),
                    dailyChange = BigDecimal.valueOf(0.00906),
                    dailyChangeRelative = BigDecimal.valueOf(0.0522),
                    lastPrice = BigDecimal.valueOf(0.18265),
                    volume = BigDecimal.valueOf(5733.37570865),
                    high = BigDecimal.valueOf(0.18581),
                    low = BigDecimal.valueOf(0.17359),
                    flashReturnRateAmountAvailable = null
                )
            ),
            TickerModel(
                friendlyName = "Litecoin",
                apiName = null,
                tickerDetails = TickerDetails(
                    symbol = "LTC",
                    type = TradingPair,
                    flashReturnRate = null,
                    bid = BigDecimal.valueOf(61.11),
                    bidPeriod = null,
                    bidSize = BigDecimal.valueOf(841.72269684),
                    ask = BigDecimal.valueOf(61.138),
                    askPeriod = null,
                    askSize = BigDecimal.valueOf(810.62121529),
                    dailyChange = BigDecimal.valueOf(3.689),
                    dailyChangeRelative = BigDecimal.valueOf(0.0642),
                    lastPrice = BigDecimal.valueOf(61.11),
                    volume = BigDecimal.valueOf(13560.61452152),
                    high = BigDecimal.valueOf(61.497),
                    low = BigDecimal.valueOf(56.205),
                    flashReturnRateAmountAvailable = null
                )
            ),
            TickerModel(
                friendlyName = "Ripple",
                apiName = null,
                tickerDetails = TickerDetails(
                    symbol = "XRP",
                    type = TradingPair,
                    flashReturnRate = null,
                    bid = BigDecimal.valueOf(0.35205),
                    bidPeriod = null,
                    bidSize = BigDecimal.valueOf(152034.45307894),
                    ask = BigDecimal.valueOf(0.35219),
                    askPeriod = null,
                    askSize = BigDecimal.valueOf(123431.48045934999),
                    dailyChange = BigDecimal.valueOf(0.01983),
                    dailyChangeRelative = BigDecimal.valueOf(0.0596),
                    lastPrice = BigDecimal.valueOf(0.35238),
                    volume = BigDecimal.valueOf(5787236.63193001),
                    high = BigDecimal.valueOf(0.35583),
                    low = BigDecimal.valueOf(0.32821),
                    flashReturnRateAmountAvailable = null
                )
            )
        )
    }
}
