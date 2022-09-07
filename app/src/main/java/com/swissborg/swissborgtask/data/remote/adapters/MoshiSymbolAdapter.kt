package com.swissborg.swissborgtask.data.remote.adapters

import com.squareup.moshi.*
import com.swissborg.swissborgtask.data.remote.responses.SymbolResponse
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class MoshiSymbolAdapter @Inject constructor() : JsonAdapter<SymbolResponse?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): SymbolResponse {
        val jsonItems = reader.readJsonValue() as List<*>
        return SymbolResponse(
            symbol = jsonItems[0].toString(),
            friendlyName = jsonItems[1].toString()
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: SymbolResponse?) {
        if (value != null) {
            val items = value::class.memberProperties.map { it.getter.call(value).toString() }
            writer.value(items.toString())
        } else writer.nullValue()
    }
}
