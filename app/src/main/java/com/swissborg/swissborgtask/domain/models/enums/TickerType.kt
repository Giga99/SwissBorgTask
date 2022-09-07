package com.swissborg.swissborgtask.domain.models.enums

enum class TickerType(val jsonValue: Char) {
    TradingPair('t'),
    FundingCurrency('f'),
    Unknown('/');

    companion object {
        fun String.toTickerType(): TickerType =
            when (this[0]) {
                TradingPair.jsonValue -> TradingPair
                FundingCurrency.jsonValue -> FundingCurrency
                else -> Unknown
            }
    }
}
