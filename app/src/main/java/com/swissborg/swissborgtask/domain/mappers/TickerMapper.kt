package com.swissborg.swissborgtask.domain.mappers

import com.swissborg.swissborgtask.data.remote.responses.TickerResponse
import com.swissborg.swissborgtask.domain.models.ui.TickerModel

fun TickerResponse.toModel(): TickerModel =
    TickerModel(
        symbol = symbol,
        type = type,
        flashReturnRate = flashReturnRate,
        bid = bid,
        bidPeriod = bidPeriod,
        bidSize = bidSize,
        ask = ask,
        askPeriod = askPeriod,
        askSize = askSize,
        dailyChange = dailyChange,
        dailyChangeRelative = dailyChangeRelative,
        lastPrice = lastPrice,
        volume = volume,
        high = high,
        low = low,
        flashReturnRateAmountAvailable = flashReturnRateAmountAvailable
    )
