package com.swissborg.swissborgtask.presentation.main

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.ui.TickerUIModel

sealed class MainEvent {
    data class SearchQueryChange(val searchQuery: String) : MainEvent()
    object SearchTickersButtonClicked : MainEvent()
}

data class MainViewState(
    val tickers: Result<List<TickerUIModel>> = Result.Loading(),
    val searchQuery: String = ""
)
