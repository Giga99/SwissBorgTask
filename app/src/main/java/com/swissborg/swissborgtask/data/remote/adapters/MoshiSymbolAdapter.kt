package com.swissborg.swissborgtask.data.remote.adapters

import com.squareup.moshi.*
import javax.inject.Inject

class MoshiSymbolAdapter @Inject constructor() : JsonAdapter<Map<String, String>?>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Map<String, String> {
        val jsonItems = (reader.readJsonValue() as List<*>)[0] as List<List<String>>
        return jsonItems.associate { it[0] to it[1] }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Map<String, String>?) {
        if (value != null) {
            val items = value.map { listOf(it.key, it.value) }
            writer.value(items.toString())
        } else writer.nullValue()
    }
}
