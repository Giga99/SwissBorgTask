package com.swissborg.swissborgtask.domain.usecases

import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTickers @Inject constructor(
    private val tickerRepository: TickersRepository
) {

    suspend operator fun invoke(searchQuery: String): Flow<List<TickerModel>> =
        tickerRepository.searchTickers(searchQuery)
}
