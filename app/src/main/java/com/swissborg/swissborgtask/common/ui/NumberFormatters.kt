package com.swissborg.swissborgtask.common.ui

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun BigDecimal.toPrice(): String {
    val format = DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.US)).apply {
        positivePrefix = "€"
        roundingMode = RoundingMode.HALF_EVEN
    }
    return format.format(this)
}

fun BigDecimal.toPercentage(): String {
    val format = DecimalFormat("#,##0.00%", DecimalFormatSymbols.getInstance(Locale.US)).apply {
        positivePrefix = "+"
        negativePrefix = "-"
    }
    return format.format(this)
}
