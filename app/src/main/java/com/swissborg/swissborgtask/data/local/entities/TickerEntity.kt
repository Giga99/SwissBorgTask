package com.swissborg.swissborgtask.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swissborg.swissborgtask.data.local.entities.TickerEntity.Companion.TABLE_NAME
import com.swissborg.swissborgtask.domain.models.enums.TickerType
import java.math.BigDecimal

@Entity(tableName = TABLE_NAME)
data class TickerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long? = null,
    @ColumnInfo(name = COLUMN_FRIENDLY_NAME)
    val friendlyName: String? = null,
    @ColumnInfo(name = COLUMN_API_NAME)
    val apiName: String? = null,
    @Embedded(prefix = PREFIX_TICKER_DETAILS)
    val tickerDetails: TickerDetailsEntity
) {
    companion object {
        const val TABLE_NAME = "tickers"
        const val COLUMN_ID = "${TABLE_NAME}_id"
        const val COLUMN_FRIENDLY_NAME = "${TABLE_NAME}_friendlyName"
        const val COLUMN_API_NAME = "${TABLE_NAME}_apiName"
        const val PREFIX_TICKER_DETAILS = "${TABLE_NAME}_tickerDetails_"
    }

    data class TickerDetailsEntity(
        @ColumnInfo(name = COLUMN_SYMBOL)
        val symbol: String,
        @ColumnInfo(name = COLUMN_TYPE)
        val type: TickerType,
        @ColumnInfo(name = COLUMN_FRR)
        val flashReturnRate: BigDecimal?,
        @ColumnInfo(name = COLUMN_BID)
        val bid: BigDecimal,
        @ColumnInfo(name = COLUMN_BID_PERIOD)
        val bidPeriod: BigDecimal?,
        @ColumnInfo(name = COLUMN_BID_SIZE)
        val bidSize: BigDecimal,
        @ColumnInfo(name = COLUMN_ASK)
        val ask: BigDecimal,
        @ColumnInfo(name = COLUMN_ASK_PERIOD)
        val askPeriod: BigDecimal?,
        @ColumnInfo(name = COLUMN_ASK_SIZE)
        val askSize: BigDecimal,
        @ColumnInfo(name = COLUMN_DAILY_CHANGE)
        val dailyChange: BigDecimal,
        @ColumnInfo(name = COLUMN_DAILY_CHANGE_RELATIVE)
        val dailyChangeRelative: BigDecimal,
        @ColumnInfo(name = COLUMN_LAST_PRICE)
        val lastPrice: BigDecimal,
        @ColumnInfo(name = COLUMN_VOLUME)
        val volume: BigDecimal,
        @ColumnInfo(name = COLUMN_HIGH)
        val high: BigDecimal,
        @ColumnInfo(name = COLUMN_LOW)
        val low: BigDecimal,
        @ColumnInfo(name = COLUMN_FFR_AVAILABLE)
        val flashReturnRateAmountAvailable: BigDecimal?
    ) {
        companion object {
            const val COLUMN_SYMBOL = "symbol"
            const val COLUMN_TYPE = "type"
            const val COLUMN_FRR = "flashReturnRate"
            const val COLUMN_BID = "bid"
            const val COLUMN_BID_PERIOD = "bidPeriod"
            const val COLUMN_BID_SIZE = "bidSize"
            const val COLUMN_ASK = "ask"
            const val COLUMN_ASK_PERIOD = "askPeriod"
            const val COLUMN_ASK_SIZE = "askSize"
            const val COLUMN_DAILY_CHANGE = "dailyChange"
            const val COLUMN_DAILY_CHANGE_RELATIVE = "dailyChangeRelative"
            const val COLUMN_LAST_PRICE = "lastPrice"
            const val COLUMN_VOLUME = "volume"
            const val COLUMN_HIGH = "high"
            const val COLUMN_LOW = "low"
            const val COLUMN_FFR_AVAILABLE = "flashReturnRateAmountAvailable"
        }
    }
}
