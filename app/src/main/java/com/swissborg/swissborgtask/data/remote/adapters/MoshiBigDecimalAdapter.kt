package com.swissborg.swissborgtask.data.remote.adapters

import com.squareup.moshi.*
import java.math.BigDecimal
import javax.inject.Inject

class MoshiBigDecimalAdapter @Inject constructor() : JsonAdapter<BigDecimal?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): BigDecimal? {
        return reader.readJsonValue()?.toString()?.let { BigDecimal(it) }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        if (value != null) writer.value(value.toEngineeringString())
        else writer.nullValue()
    }
}
