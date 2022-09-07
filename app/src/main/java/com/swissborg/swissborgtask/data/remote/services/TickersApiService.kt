package com.swissborg.swissborgtask.data.remote.services

import com.swissborg.swissborgtask.data.remote.responses.SymbolResponse
import com.swissborg.swissborgtask.data.remote.responses.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TickersApiService {

    @GET("v2/tickers")
    suspend fun getTickers(
        @Query("symbols") symbols: String
    ): Response<List<TickerResponse>>

    @GET("v2/conf/pub:map:currency:{detail}")
    suspend fun getCurrencySymbol(
        @Path("detail") detail: String
    ): Response<List<List<SymbolResponse>>>
}
