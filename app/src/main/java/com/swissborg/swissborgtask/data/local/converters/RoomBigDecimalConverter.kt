package com.swissborg.swissborgtask.data.local.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class RoomBigDecimalConverter {
    @TypeConverter
    fun from(valueString: String?): BigDecimal? {
        return valueString?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun to(value: BigDecimal?): String? {
        return value?.toEngineeringString()
    }
}
