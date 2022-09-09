package com.swissborg.swissborgtask.presentation.main

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.common.wrappers.connectivity.NetworkStatus
import com.swissborg.swissborgtask.domain.models.ui.TickerModel

sealed class MainEvent {
    data class SearchQueryChange(val searchQuery: String) : MainEvent()
    object SearchTickersButtonClicked : MainEvent()
}

data class MainViewState(
    val fetchTickersResult: Result<Unit> = Result.Loading(),
    val tickers: List<TickerModel> = emptyList(),
    val searchQuery: String = "",
    val networkStatus: NetworkStatus = NetworkStatus.CONNECTED
)
