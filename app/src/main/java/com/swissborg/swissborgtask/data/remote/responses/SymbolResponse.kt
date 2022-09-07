package com.swissborg.swissborgtask.data.remote.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SymbolResponse(
    val symbol: String,
    val friendlyName: String
)
