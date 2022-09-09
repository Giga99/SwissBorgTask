package com.swissborg.swissborgtask.domain.mappers

import com.swissborg.swissborgtask.data.local.entities.TickerEntity
import com.swissborg.swissborgtask.data.local.entities.TickerEntity.TickerDetailsEntity
import com.swissborg.swissborgtask.data.remote.responses.TickerResponse
import com.swissborg.swissborgtask.domain.models.ui.TickerDetails
import com.swissborg.swissborgtask.domain.models.ui.TickerModel

fun TickerResponse.toEntity(): TickerDetailsEntity =
    TickerDetailsEntity(
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

fun TickerEntity.toModel(): TickerModel =
    TickerModel(
        friendlyName = friendlyName,
        apiName = apiName,
        tickerDetails = tickerDetails.toModel(symbol)
    )

fun TickerDetailsEntity.toModel(symbol: String): TickerDetails =
    TickerDetails(
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

fun TickerModel.toEntity(): TickerEntity =
    TickerEntity(
        friendlyName = friendlyName,
        apiName = apiName,
        symbol = tickerDetails.symbol,
        tickerDetails = tickerDetails.toEntity()
    )

fun TickerDetails.toEntity(): TickerDetailsEntity =
    TickerDetailsEntity(
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
