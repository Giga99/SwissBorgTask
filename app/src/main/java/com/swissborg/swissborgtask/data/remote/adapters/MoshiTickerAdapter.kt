package com.swissborg.swissborgtask.data.remote.adapters

import com.squareup.moshi.*
import com.swissborg.swissborgtask.data.remote.responses.TickerResponse
import com.swissborg.swissborgtask.domain.models.enums.TickerType
import com.swissborg.swissborgtask.domain.models.enums.TickerType.Companion.toTickerType
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class MoshiTickerAdapter @Inject constructor() : JsonAdapter<TickerResponse?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): TickerResponse {
        val jsonItems = reader.readJsonValue() as List<*>

        val symbol = jsonItems[0].toString()
        val tickerType = symbol.toTickerType()

        val bidIndex = if (tickerType == TickerType.TradingPair) 1 else 2
        val bidSizeIndex = if (tickerType == TickerType.TradingPair) 2 else 4
        val askIndex = bidSizeIndex + 1
        val askSizeIndex = if (tickerType == TickerType.TradingPair) 4 else 7
        val dailyChangeIndex = askSizeIndex + 1
        val dailyChangeRelativeIndex = dailyChangeIndex + 1
        val lastPriceIndex = dailyChangeRelativeIndex + 1
        val volumeIndex = lastPriceIndex + 1
        val highIndex = volumeIndex + 1
        val lowIndex = highIndex + 1

        return TickerResponse(
            symbol = symbol
                .removeRange(
                    if (symbol.contains(":")) symbol.length - 4 else symbol.length - 3,
                    symbol.length
                )
                .removeRange(0, 1),
            type = tickerType,
            flashReturnRate = if (tickerType == TickerType.FundingCurrency) jsonItems[1].toString().toBigDecimal() else null,
            bid = jsonItems[bidIndex].toString().toBigDecimal(),
            bidPeriod = if (tickerType == TickerType.FundingCurrency) jsonItems[3].toString().toBigDecimal() else null,
            bidSize = jsonItems[bidSizeIndex].toString().toBigDecimal(),
            ask = jsonItems[askIndex].toString().toBigDecimal(),
            askPeriod = if (tickerType == TickerType.FundingCurrency) jsonItems[6].toString().toBigDecimal() else null,
            askSize = jsonItems[askSizeIndex].toString().toBigDecimal(),
            dailyChange = jsonItems[dailyChangeIndex].toString().toBigDecimal(),
            dailyChangeRelative = jsonItems[dailyChangeRelativeIndex].toString().toBigDecimal(),
            lastPrice = jsonItems[lastPriceIndex].toString().toBigDecimal(),
            volume = jsonItems[volumeIndex].toString().toBigDecimal(),
            high = jsonItems[highIndex].toString().toBigDecimal(),
            low = jsonItems[lowIndex].toString().toBigDecimal(),
            flashReturnRateAmountAvailable = jsonItems.getOrNull(16)?.toString()?.toBigDecimal()
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: TickerResponse?) {
        if (value != null) {
            val items = value::class.memberProperties.map { it.getter.call(value).toString() }
            writer.value(items.toString())
        } else writer.nullValue()
    }
}
