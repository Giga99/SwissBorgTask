package com.swissborg.swissborgtask.data.datasource

import com.swissborg.swissborgtask.common.core.Constants
import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.common.wrappers.dispatchers.DispatcherProvider
import com.swissborg.swissborgtask.data.local.TickersDao
import com.swissborg.swissborgtask.data.local.entities.TickerEntity
import com.swissborg.swissborgtask.data.remote.services.TickersApiService
import com.swissborg.swissborgtask.domain.mappers.toEntity
import com.swissborg.swissborgtask.domain.mappers.toModel
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DefaultTickersRepositoryImpl @Inject constructor(
    private val tickersApiService: TickersApiService,
    private val tickersDao: TickersDao,
    private val dispatcherProvider: DispatcherProvider
) : TickersRepository {

    override suspend fun fetchTickers(symbols: String): Result<Unit> =
        withContext(dispatcherProvider.io()) {
            try {
                val response = tickersApiService.getTickers(symbols).body() ?: emptyList()
                tickersDao.insertTickers(
                    response.map {
                        TickerEntity(
                            symbol = it.symbol,
                            tickerDetails = it.toEntity()
                        )
                    }
                )
                Result.Success(Unit)
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.message ?: "")
            }
        }

    override suspend fun fetchCurrencySymbol(detail: String): Result<Unit> =
        withContext(dispatcherProvider.io()) {
            try {
                val response = tickersApiService.getCurrencySymbol(detail).body() ?: emptyMap()
                for ((key, value) in response) {
                    if (detail == Constants.GET_FRIENDLY_NAME_PATH)
                        tickersDao.updateFriendlyName(key, value)
                    else
                        tickersDao.updateApiName(key, value)
                }
                Result.Success(Unit)
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e.message ?: "")
            }
        }

    override suspend fun searchTickers(searchQuery: String): Flow<List<TickerModel>> =
        tickersDao.searchTickers("%$searchQuery%").map { it.map { it.toModel() } }
}
