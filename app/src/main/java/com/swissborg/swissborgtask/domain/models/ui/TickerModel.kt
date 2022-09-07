package com.swissborg.swissborgtask.domain.models.ui

import com.swissborg.swissborgtask.domain.models.enums.TickerType
import java.math.BigDecimal

data class TickerModel(
    val symbol: String,
    val type: TickerType,
    val flashReturnRate: BigDecimal?,
    val bid: BigDecimal,
    val bidPeriod: BigDecimal?,
    val bidSize: BigDecimal,
    val ask: BigDecimal,
    val askPeriod: BigDecimal?,
    val askSize: BigDecimal,
    val dailyChange: BigDecimal,
    val dailyChangeRelative: BigDecimal,
    val lastPrice: BigDecimal,
    val volume: BigDecimal,
    val high: BigDecimal,
    val low: BigDecimal,
    val flashReturnRateAmountAvailable: BigDecimal?
)
