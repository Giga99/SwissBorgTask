package com.swissborg.swissborgtask.data.datasource

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.data.remote.services.TickersApiService
import com.swissborg.swissborgtask.domain.mappers.toModel
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import com.swissborg.swissborgtask.domain.repositories.TickerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val tickersApiService: TickersApiService
) : TickerRepository {

    override suspend fun getTickers(symbols: String): Result<List<TickerModel>> =
        withContext(Dispatchers.IO) {
            try {
                val response = tickersApiService.getTickers(symbols).body() ?: emptyList()
                Result.Success(response.map { it.toModel() })
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.message ?: "")
            }
        }

    override suspend fun getCurrencySymbol(detail: String): Result<Map<String, String>> =
        withContext(Dispatchers.IO) {
            try {
                val response = tickersApiService.getCurrencySymbol(detail).body() ?: emptyMap()
                Result.Success(response)
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.message ?: "")
            }
        }
}
